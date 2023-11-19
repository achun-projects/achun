package site.achun.gallery.app.service.rules.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.service.AlbumRecordService;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.gallery.app.service.pictures.PictureConvertService;
import site.achun.gallery.app.service.rules.QueryHandler;
import site.achun.gallery.client.module.rules.beans.FromUnit;
import site.achun.gallery.client.module.rules.beans.RuleType;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AlbumUnitRandomQueryHandler implements QueryHandler {

    private final AlbumRecordService albumRecordService;
    private final PicturesService picturesService;
    private final PictureConvertService pictureConvertService;

    @Override
    public boolean match(RuleType type) {
        return RuleType.ALBUM_UNIT_RANDOM.equals(type);
    }

    @Override
    public List<String> query(Object rule) {
        FromUnit fromUnit = JSON.parseObject(rule.toString(), FromUnit.class);
        String randomUnitCode = albumRecordService.randomQuery(fromUnit.getAlbumCodes()).getSetCode();
        List<Pictures> pics = picturesService.lambdaQuery()
                .eq(Pictures::getSetCode, randomUnitCode)
                .list();
        if(CollUtil.isEmpty(pics)){
            return new ArrayList<>();
        }else{
            return RandomUtil.randomEleList(pics,fromUnit.getMaxCount()).stream()
                    .map(Pictures::getFileCode)
                    .collect(Collectors.toList());
        }
    }
}
