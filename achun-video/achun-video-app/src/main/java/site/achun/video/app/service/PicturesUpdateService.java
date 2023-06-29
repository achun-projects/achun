package site.achun.video.app.service;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.module.file.response.MediaFileResponse;
import site.achun.video.app.generator.domain.AlbumRecord;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.mapper.AlbumRecordMapper;
import site.achun.video.app.generator.mapper.PicturesMapper;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.support.api.enums.Deleted;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PicturesUpdateService {

    private final PicturesMapper picturesMapper;
    private final AlbumService albumService;
    private final AlbumRecordMapper albumRecordMapper;

    public void update(MediaFileResponse fileInfo){
        // 保存同步文件
        Pictures pic = new Pictures();
        pic.setFileCode(fileInfo.getFileCode());
        pic.setFileName(fileInfo.getFileName());
        pic.setSetCode(fileInfo.getUnitCode());
        pic.setSuffix(fileInfo.getSuffix());
        pic.setSize(fileInfo.getSize());
        pic.setDeleted(Deleted.NO.getStatus());
        pic.setWidth(fileInfo.getWidth());
        pic.setHeight(fileInfo.getHeight());
        pic.setWh(fileInfo.getWh());
        pic.setDuration(fileInfo.getDuration());
        pic.setAtime(LocalDateTime.now());
        pic.setCtime(LocalDateTime.now());
        pic.setUtime(LocalDateTime.now());
        int result = picturesMapper.replaceInto(pic);
        log.info("fileCode:{},replaceInto picture:{}",pic.getFileCode(),result);
        // 创建关联关系
        if(StrUtil.isNotBlank(fileInfo.getThirdId())
                && !fileInfo.getThirdId().equals("-1")
                && albumService.getByCode(fileInfo.getThirdId())!=null
        ){
            AlbumRecord albumRecord = new AlbumRecord();
            albumRecord.setAlbumCode(fileInfo.getThirdId());
            albumRecord.setSetCode(fileInfo.getUnitCode());
            result = albumRecordMapper.replaceInto(albumRecord);
            log.info("fileCode:{},replaceInto album_record:{},albumCode:{},unitCode:{}",
                    pic.getFileCode(),result,fileInfo.getThirdId(),fileInfo.getUnitCode());
        }
    }

}
