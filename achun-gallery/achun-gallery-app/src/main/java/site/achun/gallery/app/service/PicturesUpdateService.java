package site.achun.gallery.app.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.enums.Type;
import site.achun.file.client.module.file.response.FileInfoResponse;
import site.achun.file.client.module.file.response.detail.Image;
import site.achun.file.client.module.file.response.detail.Video;
import site.achun.gallery.app.generator.domain.AlbumRecord;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.mapper.AlbumRecordMapper;
import site.achun.gallery.app.generator.mapper.PicturesMapper;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.support.api.enums.Deleted;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class PicturesUpdateService {

    private final PicturesMapper picturesMapper;
    private final AlbumService albumService;
    private final AlbumRecordMapper albumRecordMapper;

    public void update(FileInfoResponse fileInfo){
        // 保存同步文件
        Pictures pic = new Pictures();
        pic.setFileCode(fileInfo.getFileCode());
        pic.setFileName(fileInfo.getFileName());
        pic.setSetCode(fileInfo.getUnitCode());
        pic.setSuffix(fileInfo.getSuffix());
        pic.setSize(fileInfo.getSize());
        pic.setDeleted(Deleted.NO.getStatus());
        Type type = Type.parse(fileInfo.getType()).get();
        switch (type){
            case IMAGE:
                Image image = BeanUtil.toBean(fileInfo.getDetail(),Image.class);
                pic.setWidth(image.getWidth());
                pic.setHeight(image.getHeight());
                pic.setWh(image.getWh());
                break;
            case VIDEO:
                Video video = BeanUtil.toBean(fileInfo.getDetail(),Video.class);
                pic.setWidth(video.getWidth());
                pic.setHeight(video.getHeight());
                pic.setWh(video.getWh());
                pic.setDuration(video.getDuration());
        }
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
