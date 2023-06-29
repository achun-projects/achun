package site.achun.video.app.service.unit;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.AlbumRecord;
import site.achun.video.app.generator.domain.FileSet;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.service.AlbumRecordService;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.video.app.generator.service.FileSetService;
import site.achun.video.app.generator.service.PicturesService;
import site.achun.video.client.constant.GalleryRC;
import site.achun.video.client.module.fileset.request.AnewFileSet;
import site.achun.video.client.module.fileset.response.FileSetResponse;
import site.achun.support.api.exception.RspException;

import java.time.LocalDateTime;

/**
 * 重组一个文件集合
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/13
 */
@Slf4j
@Service
@AllArgsConstructor
public class AnewFileSetService {

    private final FileSetService fileSetService;
    private final AlbumRecordService albumRecordService;
    private final PicUnitQueryExecute fileSetQueryExecute;
    private final PicturesService picturesService;
    private final AlbumService albumService;
    public FileSetResponse anewFileset(AnewFileSet anewFileset) {
        if(fileSetService.getByCode(anewFileset.getSetCode())!=null){
            // TODO 存在分组，将其他文件迁移至该分组，并更新信息
            throw new RspException(GalleryRC.CODE_UN_IMPLEMENTS);
        }else{
            // 分组不存在，新增记录
            FileSet fileSet = FileSet.builder()
                    .name(anewFileset.getName())
                    .code(anewFileset.getSetCode())
                    .build();
            fileSetService.save(fileSet);
            // 更新file表fileset_code字段
            picturesService.lambdaUpdate()
                    .set(Pictures::getSetCode,anewFileset.getSetCode())
                    .set(Pictures::getUtime, LocalDateTime.now())
                    .in(Pictures::getFileCode,anewFileset.getFileCodes())
                    .update();
            // 添加album_record记录
            AlbumRecord record = AlbumRecord.builder()
                    .setCode(anewFileset.getSetCode())
                    .albumCode(anewFileset.getAlbumCode())
                    .ctime(LocalDateTime.now())
                    .build();
            albumRecordService.save(record);
            albumService.updateRecordUtime(anewFileset.getAlbumCode());
            // 判断之前的分组是否有为空的，删除。
            return fileSetQueryExecute.queryByUnitCode(anewFileset.getSetCode());
        }
    }
}
