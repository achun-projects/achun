package site.achun.gallery.app.service.gallery_group;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.GalleryGroup;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.generator.service.GalleryGroupService;
import site.achun.gallery.app.service.gallery_group.response.GalleryGroupResponse;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/5/7 0:14
 */
@Slf4j
@Service
public class MyGroupService {

    @Autowired
    private GalleryGroupService galleryGroupService;

    @Autowired
    private GalleryGroupRecordService galleryGroupRecordService;


    public GalleryGroupResponse selectForNames(String listCode){
        GalleryGroupRecord record = galleryGroupRecordService.lambdaQuery()
                .eq(GalleryGroupRecord::getListCode, listCode)
                .last("limit 1")
                .one();
        if(record==null) {
            log.info("listCode:{}查询不到分组",listCode);
            return null;
        }
        Function<String, GalleryGroup> queryMethod = (groupCode)->
                galleryGroupService.lambdaQuery()
                    .eq(GalleryGroup::getGroupCode,groupCode)
                    .one();

        GalleryGroup group = null;
        String groupCode = record.getGroupCode();
        String groupCodes = "";
        String names = "";
        do{
            group = queryMethod.apply(groupCode);
            groupCode = group.getParentGroupCode();
            names = " / " + group.getName() + names;
            groupCodes = "," + group.getGroupCode() + groupCodes;
        }while(StrUtil.isNotEmpty(groupCode));
        return GalleryGroupResponse.builder()
                .groupNames(names)
                .groupCodes(Arrays.asList(groupCodes.split(",")).stream().filter(str->StrUtil.isNotEmpty(str)).collect(Collectors.toList()))
                .groupCode(groupCode)
                .build();
    }

}
