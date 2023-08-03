package site.achun.video.app.generator.service;

import cn.hutool.core.collection.CollUtil;
import site.achun.video.app.generator.domain.TagsMap;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.*;
import java.util.stream.Collectors;

/**
* @author Administrator
* @description 针对表【tags_map】的数据库操作Service
* @createDate 2023-06-29 18:54:59
*/
public interface TagsMapService extends IService<TagsMap> {
    default List<TagsMap> getTagsMapByObjectCode(String objectCode){
        return this.lambdaQuery()
                .eq(TagsMap::getObjectCode,objectCode)
                .list();
    }
    default List<TagsMap> queryByObjectCodes(Collection<String> objectCodes){
        List<TagsMap> list = this.lambdaQuery()
                .in(TagsMap::getObjectCode, objectCodes)
                .list();
        if(CollUtil.isEmpty(list)) return new ArrayList<>();
        return list;
    }
    default List<TagsMap> queryByObjectCode(String objectCode){
        List<TagsMap> list = this.lambdaQuery()
                .eq(TagsMap::getObjectCode, objectCode)
                .list();
        if(CollUtil.isEmpty(list)) return new ArrayList<>();
        return list;
    }

    default Set<String> getTagsByObjectCode(String objectCode){
        List<TagsMap> tagsMapList = this.lambdaQuery()
                .eq(TagsMap::getObjectCode, objectCode)
                .list();
        if(CollUtil.isEmpty(tagsMapList)){
            return new HashSet<>();
        }
        return tagsMapList.stream()
                .map(TagsMap::getTagName)
                .collect(Collectors.toSet());
    }


    default List<TagsMap> getSameTagsByObjectCode(String objectCode){
        Set<String> tags = this.getTagsByObjectCode(objectCode);
        if(CollUtil.isEmpty(tags)) return new ArrayList<>();
        return this.lambdaQuery()
                .in(TagsMap::getTagName,tags)
                .list();
    }
}
