package site.achun.gallery.app.service.pictures;

import cn.hutool.core.collection.CollUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.Pictures;
import site.achun.gallery.app.generator.service.PicturesService;
import site.achun.support.api.response.Rsp;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class PictureUpdateService {

    private final PicturesService picturesService;

    private final AfterPictureDeleteExecute afterPictureDeleteExecute;

    public Rsp<Integer> remove(Collection<String> fileCodes) {
        List<Pictures> pictures = picturesService.lambdaQuery()
                .in(Pictures::getFileCode, fileCodes)
                .list();
        if(CollUtil.isEmpty(pictures)){
            log.info("不存在图片，无需删除，：{}",fileCodes);
            return Rsp.success(0);
        }
        // 删除文件本体
        picturesService.lambdaUpdate()
                .in(Pictures::getFileCode,fileCodes)
                .remove();
        // 异步清除脏数据
        Set<String> fileSetCodes = pictures.stream()
                .map(Pictures::getSetCode)
                .collect(Collectors.toSet());
        afterPictureDeleteExecute.execute(fileCodes,fileSetCodes);
        return Rsp.success(pictures.size(),"删除成功");
    }
}
