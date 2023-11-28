package site.achun.gallery.app.service.album_record;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.dir.FileDirQueryClient;
import site.achun.file.client.module.dir.request.ByDirCode;
import site.achun.file.client.module.dir.response.DirResponse;
import site.achun.gallery.app.service.unit.PicUnitUpdateExecute;
import site.achun.gallery.client.module.pic_unit.request.CreateOrUpdatePicUnit;
import site.achun.support.api.response.Rsp;

import java.util.List;

/**
 * 从dir同步数据到相册
 */
@Slf4j
@Service
@AllArgsConstructor
public class AlbumRecordAsyncFromDirService {

    private final AlbumRecordUpdateExecute albumRecordUpdateExecute;
    private final FileDirQueryClient fileDirQueryClient;
    private final PicUnitUpdateExecute picUnitUpdateExecute;
    @Async
    public void startAsync(String albumCode,String dirCode,String userCode){
        Rsp<List<DirResponse>> resp = fileDirQueryClient.queryDeep(ByDirCode.builder().dirCode(dirCode).build());
        for (DirResponse dir : resp.getData()) {
            albumRecordUpdateExecute.replaceInfo(albumCode,dir.getDirCode());
            CreateOrUpdatePicUnit createUnitReq = CreateOrUpdatePicUnit.builder()
                    .unitCode(dir.getDirCode())
                    .name(dir.getName())
                    .userCode(userCode)
                    .build();
            picUnitUpdateExecute.createOrUpdate(createUnitReq);
        }
        log.info("同步任务执行结束，albumCode:{},dirCode:{}",albumCode,dirCode);
    }
}
