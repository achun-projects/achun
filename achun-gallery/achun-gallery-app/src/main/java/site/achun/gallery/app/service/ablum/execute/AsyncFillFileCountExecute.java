package site.achun.gallery.app.service.ablum.execute;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Album;
import site.achun.gallery.app.generator.service.AlbumService;
import site.achun.gallery.app.service.pictures.MyPictureService;

import java.util.Map;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class AsyncFillFileCountExecute {

    private final AlbumService albumSerivce;
    private final MyPictureService myPictureService;

    @Async
    public void asyncFillFileCount(Set<String> albumCodes){
        Map<String, Integer> albumCountMap = myPictureService.queryAlbumFileCounts(albumCodes);
        for (String albumCode : albumCodes) {
            albumSerivce.lambdaUpdate()
                    .eq(Album::getAlbumCode,albumCode)
                    .set(Album::getFileCount,albumCountMap.get(albumCode))
                    .update();
        }
    }
}
