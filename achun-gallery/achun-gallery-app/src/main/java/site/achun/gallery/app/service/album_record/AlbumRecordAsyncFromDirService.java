package site.achun.gallery.app.service.album_record;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.FileQueryClient;
import site.achun.file.client.module.file.request.QueryFilePageByDirCode;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.gallery.app.service.PicturesUpdateService;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 从dir同步数据到相册
 */
@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordAsyncFromDirService {

    private final FileQueryClient fileQueryClient;

    private final PicturesUpdateService picturesUpdateService;
    private final AlbumRecordUpdateExecute albumRecordUpdateExecute;
    @Async
    public void startAsync(String albumCode,String dirCode){
        int page = 1,size = 500;
        List<FileInfoResponse> list = null;
        while((list = get(dirCode,page++,size)) != null){
            List<String> unitCodes = list.stream()
                    .map(FileInfoResponse::getUnitCode)
                    .distinct()
                    .collect(Collectors.toList());
            for (String unitCode : unitCodes) {
                albumRecordUpdateExecute.replaceInfo(albumCode,unitCode);
            }
            for (FileInfoResponse fileInfoResponse : list) {
                picturesUpdateService.update(fileInfoResponse);
            }
        }
        log.info("同步任务执行结束，albumCode:{},dirCode:{},page:{},size:{}",albumCode,dirCode,page,size);
    }

    private List<FileInfoResponse> get(String dirCode, int page, int size){
        QueryFilePageByDirCode req = new QueryFilePageByDirCode();
        req.setDirCode(dirCode);
        req.setOnlyThis(false);
        req.setPage(page);
        req.setSize(size);
        Rsp<RspPage<FileInfoResponse>> response = fileQueryClient.queryFileList(req);
        if(response.hasData() && CollUtil.isNotEmpty(response.getData().getRows())){
            return response.getData().getRows();
        }else{
            return null;
        }
    }
}
