package site.achun.gallery.app.service;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.gallery.app.generator.domain.AlbumRecord;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.AlbumRecordMapper;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.support.api.enums.Deleted;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PicturesUpdateService {

    private final PicturesService picturesService;
    private final AlbumService albumService;
    private final AlbumRecordMapper albumRecordMapper;

    public void update(FileInfoResponse fileInfo){
        // 保存同步文件
        Pictures picture = picturesService.getByFileCode(fileInfo.getFileCode());
        if(picture != null ){
            picture.setFileName(fileInfo.getFileName());
            picture.setSuffix(fileInfo.getSuffix());
            picture.setSize(fileInfo.getSize());
            picture.setWidth(fileInfo.getWidth());
            picture.setHeight(fileInfo.getHeight());
            picture.setWh(fileInfo.getWh());
            picture.setDuration(fileInfo.getDuration());
            picture.setAtime(LocalDateTime.now());
            picture.setUtime(LocalDateTime.now());
            picturesService.updateById(picture);
            log.info("update pictures,fileCode:{}",picture.getFileCode());
        }else{
            picture = new Pictures();
            picture.setFileCode(fileInfo.getFileCode());
            picture.setFileName(fileInfo.getFileName());
            picture.setSetCode(fileInfo.getUnitCode());
            picture.setSuffix(fileInfo.getSuffix());
            picture.setSize(fileInfo.getSize());
            picture.setDeleted(Deleted.NO.getStatus());
            picture.setWidth(fileInfo.getWidth());
            picture.setHeight(fileInfo.getHeight());
            picture.setWh(fileInfo.getWh());
            picture.setDuration(fileInfo.getDuration());
            picture.setAtime(LocalDateTime.now());
            picture.setCtime(LocalDateTime.now());
            picture.setUtime(LocalDateTime.now());
            picturesService.save(picture);
            log.info("save new pictures,fileCode:{}",picture.getFileCode());
        }
        // 创建关联关系
        if(StrUtil.isNotBlank(fileInfo.getThirdId())
                && !fileInfo.getThirdId().equals("-1")
                && albumService.getByCode(fileInfo.getThirdId())!=null
        ){
            AlbumRecord albumRecord = new AlbumRecord();
            albumRecord.setAlbumCode(fileInfo.getThirdId());
            albumRecord.setSetCode(fileInfo.getUnitCode());
            int result = albumRecordMapper.replaceInto(albumRecord);
            log.info("fileCode:{},replaceInto album_record:{},albumCode:{},unitCode:{}",
                    picture.getFileCode(),result,fileInfo.getThirdId(),fileInfo.getUnitCode());
        }

    }

}
