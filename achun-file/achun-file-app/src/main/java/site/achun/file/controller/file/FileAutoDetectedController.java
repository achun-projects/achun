package site.achun.file.controller.file;

import cn.hutool.core.collection.CollUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import site.achun.file.client.module.file.request.QueryFilePage;
import site.achun.file.client.module.file.response.FileLocalInfoResponse;
import site.achun.file.service.file.FileQueryService;
import site.achun.gallery.client.module.album_record.AlbumRecordUpdateV4Client;
import site.achun.support.api.response.RspPage;
import site.achun.updown.client.module.detected.StorageDetectedClient;
import site.achun.updown.client.module.detected.request.QueryFileExist;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Tag(name = "文件查询")
@RequiredArgsConstructor
@RestController
public class FileAutoDetectedController {

    private final StorageDetectedClient storageDetectedClient;

    private final FileQueryService fileQueryService;

    private final AlbumRecordUpdateV4Client albumRecordUpdateV4Client;

    @Async
    @GetMapping("/file/detected/test")
    public void sendMessageTest(){
        String storageCode = "226206d8c9be4bd8a33791e03df1ef33";
        Integer page = 1;
        while(true){
            QueryFilePage query = new QueryFilePage();
            query.setSize(100);
            query.setPage(page++);
            query.setStorageCode(storageCode);
            RspPage<FileLocalInfoResponse> pageRsult = fileQueryService.queryFileLocalInfoPage(query);
            if(CollUtil.isEmpty(pageRsult.getRows())){
                log.info("该Storage[{}]文件已经扫描完毕",storageCode);
                return;
            }
            Set<String> noExistFileCodes = new HashSet<>();
            for (FileLocalInfoResponse row : pageRsult.getRows()) {
                QueryFileExist queryFileExist = QueryFileExist.builder()
                        .storageRootPath(row.getStorage().getPath())
                        .inStoragePath(row.getInStoragePath())
                        .build();
                Boolean exist = storageDetectedClient.fileExist(queryFileExist).getData();
                log.info("{}文件是否存在：{}", row.getFileCode(),exist);

                if(!exist){
                    noExistFileCodes.add(row.getFileCode());
                }
            }
            if(CollUtil.isNotEmpty(noExistFileCodes)){
                albumRecordUpdateV4Client.deleteBatchRecords(noExistFileCodes);
            }
        }
    }
}
