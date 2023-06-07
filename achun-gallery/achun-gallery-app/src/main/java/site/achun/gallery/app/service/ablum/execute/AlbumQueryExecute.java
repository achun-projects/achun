package site.achun.gallery.app.service.ablum.execute;

import cn.hutool.core.bean.BeanUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Album;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.service.gallery_group.MyGroupService;
import site.achun.gallery.app.service.gallery_group.response.GalleryGroupResponse;
import site.achun.gallery.app.utils.DateTimeUtil;
import site.achun.gallery.client.constant.GalleryRC;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.exception.RspException;

@Slf4j
@Service
@AllArgsConstructor
public class AlbumQueryExecute {

    private final MyGroupService myGroupService;
    private final GalleryGroupRecordService galleryGroupRecordService;
    private final AlbumService albumService;

    public AlbumResponse queryByCode(String albumCode){
        return toAlbumResponse(albumService.getByCode(albumCode));
    }
    public AlbumResponse toAlbumResponse(Album album){
        AlbumResponse albumResponse = BeanUtil.toBean(album, AlbumResponse.class);
        albumResponse.setRecentAtime(DateTimeUtil.parse(album.getRecordUtime()));

        // 补充分组名
        GalleryGroupResponse galleryGroup = myGroupService.selectForNames(album.getAlbumCode());
        if(galleryGroup == null){
            throw new RspException(GalleryRC.GROUP_NOT_EXIST,"相册编码：%s对应的分组不存在",album.getAlbumCode());
        }
        albumResponse.setGroupNames(galleryGroup.getGroupNames());
        albumResponse.setGroupCode(galleryGroup.getGroupCode());
        albumResponse.setGroupCodes(galleryGroup.getGroupCodes());
        return albumResponse;
    }
}
