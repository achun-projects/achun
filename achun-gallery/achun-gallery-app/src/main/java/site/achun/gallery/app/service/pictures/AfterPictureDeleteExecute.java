package site.achun.gallery.app.service.pictures;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.domain.BoardRecord;
import site.achun.gallery.app.generator.domain.FileSet;
import site.achun.gallery.app.generator.domain.AlbumRecord;
import site.achun.gallery.app.generator.service.AlbumRecordService;
import site.achun.gallery.app.generator.service.BoardRecordService;
import site.achun.gallery.app.generator.service.FileSetService;
import site.achun.gallery.app.generator.service.PicturesService;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/9/30 15:26
 */
@Slf4j
@Service
@AllArgsConstructor
public class AfterPictureDeleteExecute {
    private final PicturesService picturesService;
    private final BoardRecordService boardRecordService;
    private final FileSetService fileSetService;
    private final AlbumRecordService albumRecordService;
    @Async
    public void execute(Collection<String> fileCodes,Collection<String> fileUnitCodes){
        log.info("开始异步删除文件相关记录,fileCodes:{},fileSetCodes:{}",fileCodes,fileUnitCodes);
        // # 删除画板关联关系
        boardRecordService.lambdaUpdate()
                .in(BoardRecord::getFileCode,fileCodes)
                .remove();
        // # 删除空的分组记录，删除空的相册分组记录
        // 分析哪些分组编码对应的记录空了
        List<Pictures> picturesBySetCode = picturesService.lambdaQuery()
                .in(Pictures::getSetCode, fileUnitCodes)
                .list();
        Map<String,Pictures> picturesMap = new HashMap<>();
        if(CollUtil.isNotEmpty(picturesBySetCode)){
            picturesMap = picturesBySetCode.stream()
                    .collect(Collectors.toMap(Pictures::getSetCode,v->v,(v1,v2)->v1));
        }
        Map<String, Pictures> finalPicturesMap = picturesMap;
        Set<String> deleteSetCodes = fileUnitCodes.stream()
                .filter(setCode -> !finalPicturesMap.containsKey(setCode))
                .collect(Collectors.toSet());
        // 删除对应记录
        boolean removeFileSet = fileSetService.lambdaUpdate()
                .in(FileSet::getCode, deleteSetCodes)
                .remove();
        boolean removeAlbumRecord = albumRecordService.lambdaUpdate()
                .in(AlbumRecord::getSetCode, deleteSetCodes)
                .remove();
        log.info("删除分组记录：{}，删除相册记录：{}，删除的setCodes：{}",removeFileSet,removeAlbumRecord,deleteSetCodes);
    }
}
