package site.achun.video.app.service.ablum;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.service.AlbumService;
import site.achun.video.app.service.ablum.execute.AlbumCreateExecute;
import site.achun.video.app.service.ablum.execute.AlbumQueryExecute;
import site.achun.video.app.service.ablum.execute.AlbumRemoveExecute;
import site.achun.video.app.service.ablum.execute.AlbumUpdateExecute;
import site.achun.video.client.module.album.request.CreateOrUpdateAlbum;
import site.achun.video.client.module.album.request.RemoveAlbumRequest;
import site.achun.video.client.module.album.response.AlbumResponse;
import site.achun.support.api.response.Rsp;


@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumUpdateService {

    private final AlbumService albumService;

    private final AlbumQueryExecute albumQueryExecute;
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
        return albumQueryExecute.queryByCode(albumCode);
    }

    public Rsp<Boolean> removeWhenEmpty(RemoveAlbumRequest request) {
       return albumRemoveExecute.removeWhenEmpty(request);
    }

}
