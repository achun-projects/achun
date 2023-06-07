package site.achun.gallery.app.service.ablum;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.service.ablum.execute.AlbumCreateExecute;
import site.achun.gallery.app.service.ablum.execute.AlbumRemoveExecute;
import site.achun.gallery.app.service.ablum.execute.AlbumUpdateExecute;
import site.achun.gallery.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.gallery.client.module.album.request.RemoveAlbumRequest;
import site.achun.gallery.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;


@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumUpdateService {

    private final AlbumService albumService;

    private final AlbumRemoveExecute albumRemoveExecute;
    private final AlbumUpdateExecute albumUpdateExecute;
    private final AlbumCreateExecute albumCreateExecute;

    public AlbumResponse createOrUpdate(CreateOrUpdateAlbum request) {
        String albumCode = null;
        if(StrUtil.isNotEmpty(request.getAlbumCode()) && albumService.getByCode(request.getAlbumCode())!=null){
            albumUpdateExecute.updateAlbum(request);
            albumCode = request.getAlbumCode();
        }else{
            albumCode = albumCreateExecute.createAlbum(request);
        }
        return ;
    }

    public Rsp<Boolean> removeWhenEmpty(RemoveAlbumRequest request) {
       return albumRemoveExecute.removeWhenEmpty(request);
    }

}
