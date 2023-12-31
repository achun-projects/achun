package site.achun.updown.app.util.big.file.upload;

import com.alibaba.fastjson2.JSON;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/26 17:23
 */
@Slf4j
public class FileUploadUtil {

    @Getter
    @Setter
    @Builder
    public static class FileInfo {
        // 路径
        private String filePath;
        // 文件名
        private String fileName;
        // uid
        private String uid;
        private Integer index;
        private Integer chunksLength;
        private Boolean uploaded;
    }

    // key为文件的uid，value为Boolean[] 表示每个文件块是否上传完成
    private static Map<String, FileInfo[]> fileUploadedCache = new ConcurrentHashMap<>();

    public synchronized static void put(String uid, Integer index, Integer chunksLength, String fileName, String filePathName) {
        FileInfo[] isChunksUploaded = fileUploadedCache.get(uid);
        if (isChunksUploaded != null) {
            if (isChunksUploaded[index] != null) {
                isChunksUploaded[index].setUploaded(true);
            } else {
                FileInfo fileInfo = FileInfo.builder()
                        .fileName(fileName)
                        .filePath(filePathName)
                        .uid(uid)
                        .index(index)
                        .chunksLength(chunksLength)
                        .uploaded(true)
                        .build();
                isChunksUploaded[index] = fileInfo;
            }
        } else {
            FileInfo[] isChunksUploaded1 = new FileInfo[chunksLength];
            isChunksUploaded1[index] = FileInfo.builder()
                    .fileName(fileName)
                    .filePath(filePathName)
                    .uid(uid)
                    .index(index)
                    .chunksLength(chunksLength)
                    .uploaded(true)
                    .build();
            fileUploadedCache.put(uid, isChunksUploaded1);
        }
    }

    // 检测是否所有的块都上传完成
    public static boolean isAllChunksUploaded(String uid) {
        FileInfo[] isChunksUpploaded = fileUploadedCache.get(uid);
        log.info(JSON.toJSONString(isChunksUpploaded));
        if (isChunksUpploaded == null) {
            throw new RuntimeException("uid: " + uid + " 对应的文件块不存在");
        }
        for (FileInfo fileInfo : isChunksUpploaded) {
            if (fileInfo == null || !fileInfo.uploaded)
                return false;
        }
        return true;
    }

    public static void delete(String uid) {
        if (!isAllChunksUploaded(uid))
            throw new RuntimeException("uid: " + uid + " 对应的文件没有上传完成，无法删除");
        if(fileUploadedCache.containsKey(uid)){
            fileUploadedCache.remove(uid);
        }
    }

    public static FileInfo[] get(String uid) {
        if(fileUploadedCache.containsKey(uid)){
            return fileUploadedCache.get(uid);
        }
        throw new RuntimeException("不存在uid: " + uid);
    }

    // 合并所有文件块
    public static void mergeChunks(String name, String uid) throws Exception {
        FileInfo[] allChunks = fileUploadedCache.get(uid);
        if (allChunks == null)
            throw new RuntimeException("uid: " + uid + " 对应的文件块不存在");
        if (!isAllChunksUploaded(uid))
            throw new RuntimeException("uid: " + uid + " 对应的文件尚未上传完成");
        File dir = new File(allChunks[0].filePath);
        if (!dir.exists())
            dir.mkdirs();
        File file = new File(allChunks[0].filePath + "/" + name);
        if (file.exists())
            file.delete();
        for (FileInfo chunk : allChunks) {
            // 因为FileCopyUtils.copy每次会关闭流，因此在循环里需要每次以append的形式开启流
            OutputStream out = new FileOutputStream(file, true);
            FileCopyUtils.copy(new FileInputStream(chunk.filePath + "/" + chunk.fileName), out);
        }
        // 融合完成所有的块后，删除缓存及实际文件
        delete(uid);
        deleteRealFileChunks(allChunks);
    }

    // 删除真实文件块
    public static void deleteRealFileChunks(FileInfo[] allChunks) {
        for (FileInfo chunk : allChunks) {
            File file = new File(chunk.filePath + "/" + chunk.fileName);
            if (file.exists())
                file.delete();
        }
    }
}
