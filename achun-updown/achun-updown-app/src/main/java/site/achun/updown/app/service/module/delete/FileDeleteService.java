package site.achun.updown.app.service.module.delete;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.FileUpdateClient;
import site.achun.file.client.module.file.request.DeleteFileRequest;
import site.achun.file.client.module.file.request.QueryByFileCode;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.support.api.response.Rsp;

import java.io.File;
import java.nio.file.Path;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileDeleteService {

    private final FileQueryClient fileQueryClient;
    private final FileUpdateClient fileUpdateClient;

    public void deleteFile(String fileCode){
        if(StrUtil.isBlank(fileCode)){
            log.info("deleteFile fileCode is Blank");
            return;
        }
        QueryByFileCode queryFile = QueryByFileCode.builder()
                .fileCode(fileCode)
                .containDeleted(true)
                .build();
        Rsp<FileLocalInfoResponse> fileInfoRsp = fileQueryClient.queryFileLocalInfo(queryFile);
        if(!fileInfoRsp.hasData()){
            log.info("deleteFile fileCode查询不到数据，fileCode:{},fileInfoRsp:{}",fileCode, JSON.toJSONString(fileInfoRsp));
            return;
        }
        FileLocalInfoResponse localFileInfo = fileInfoRsp.getData();
        File file = Path.of(localFileInfo.getStorage().getPath(),localFileInfo.getInStoragePath()).toFile();
        File smallFile = Path.of(localFileInfo.getStorage().getPath(),"_small",localFileInfo.getInStoragePath()).toFile();
        File mediumFile = Path.of(localFileInfo.getStorage().getPath(),"_medium",localFileInfo.getInStoragePath()).toFile();
        log.info("file:{} {}",file.getAbsolutePath(),file.exists());
        log.info("smallFile:{} {}",smallFile.getAbsolutePath(),smallFile.exists());
        log.info("mediumFile:{} {}",mediumFile.getAbsolutePath(),mediumFile.exists());
        if(file.exists()) file.delete();
        if(smallFile.exists()) smallFile.delete();
        if(mediumFile.exists()) mediumFile.delete();

        var resultRsp = fileUpdateClient.realDeleteFileInfo(DeleteFileRequest.builder().fileCode(fileCode).build());
        if(resultRsp.hasData() && resultRsp.getData()){
            log.info("待删除文件：{},删除结果：{}",fileCode,resultRsp.getData());
        }else{
            log.info("删除文件调用file-service真实删除时出现异常,rsp:{}",JSON.toJSONString(resultRsp));
        }

    }
}
