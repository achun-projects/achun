package site.achun.video.app.service.pictures;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.mapper.PicturesMapper;
import site.achun.video.client.dto.ListFileCount;
import site.achun.video.client.module.pictures.request.QueryByListCodes;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/5/7 23:20
 */
@Service
@RequiredArgsConstructor
public class MyPictureService {

    private final PicturesMapper picturesMapper;

    public Map<String,Integer> queryBoardFileCounts(Collection<String> boardCodes){
        Map<String, ListFileCount> result = picturesMapper.selectBoardFileCount(
                QueryByListCodes.builder()
                        .listCodes(boardCodes)
                        .build()
        );
        return result.entrySet().stream()
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().getFileCount(), (v1, v2) -> v1));
    }

    public Map<String,Integer> queryAlbumFileCounts(Collection<String> boardCodes){
        Map<String, ListFileCount> result = picturesMapper.selectAlbumsFileCount(
                QueryByListCodes.builder()
                        .listCodes(boardCodes)
                        .build()
        );
        return result.entrySet().stream()
                .collect(Collectors.toMap(k -> k.getKey(), v -> v.getValue().getFileCount(), (v1, v2) -> v1));
    }
}
