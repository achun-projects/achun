package site.achun.video.app.generator.service;

import cn.hutool.core.collection.CollUtil;
import site.achun.video.app.generator.domain.GalleryGroupRecord;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【gallery_group_record】的数据库操作Service
* @createDate 2023-05-29 17:17:29
*/
public interface GalleryGroupRecordService extends IService<GalleryGroupRecord> {

    default Set<String> queryListCodes(String groupCode){
        List<GalleryGroupRecord> list = this.lambdaQuery()
                .eq(GalleryGroupRecord::getGroupCode, groupCode)
                .list();
        if(CollUtil.isEmpty(list)){
            return CollUtil.newHashSet();
        }
        return list.stream()
                .map(GalleryGroupRecord::getListCode)
                .collect(Collectors.toSet());
    }
}
