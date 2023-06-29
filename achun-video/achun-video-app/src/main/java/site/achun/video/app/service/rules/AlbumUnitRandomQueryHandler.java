package site.achun.video.app.service.rules;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.Pictures;
import site.achun.video.app.generator.service.AlbumRecordService;
import site.achun.video.app.generator.service.PicturesService;
import site.achun.video.app.service.pictures.PictureConvertService;
import site.achun.video.app.service.rules.beans.FromUnit;
import site.achun.video.app.service.rules.beans.RuleType;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumUnitRandomQueryHandler implements QueryHandler{

    private final AlbumRecordService albumRecordService;
    private final PicturesService picturesService;
    private final PictureConvertService pictureConvertService;

    @Override
    public boolean match(RuleType type) {
        return RuleType.ALBUM_UNIT_RANDOM.equals(type);
    }

    @Override
    public List<String> query(String rule) {
        FromUnit fromUnit = JSON.parseObject(rule, FromUnit.class);
        String randomUnitCode = albumRecordService.randomQuery(fromUnit.getAlbumCodes()).getSetCode();
        List<Pictures> pics = picturesService.lambdaQuery()
                .eq(Pictures::getSetCode, randomUnitCode)
                .list();
        return pics.stream()
                .map(Pictures::getFileCode)
                .collect(Collectors.toList());
    }
}
