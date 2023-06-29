package site.achun.video.app.service.gallery_group;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.GalleryGroup;
import site.achun.video.app.generator.domain.GalleryGroupRecord;
import site.achun.video.app.generator.service.GalleryGroupRecordService;
import site.achun.video.app.generator.service.GalleryGroupService;
import site.achun.video.client.module.group.response.GroupResponse;
import site.achun.video.client.module.group.response.GroupViewResponse;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 分组转换执行器
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupConvertExecute {

    private final GalleryGroupService groupService;

    private final GalleryGroupRecordService groupRecordService;



    public List<GroupViewResponse> batchConvert(Collection<GalleryGroup> list){
        // 查询父元素
        Set<String> parentCodes = list.stream()
                .map(GalleryGroup::getParentGroupCode)
                .collect(Collectors.toSet());
        Map<String,GalleryGroup> map = new HashMap<>();
        if(CollUtil.isNotEmpty(parentCodes)){
            map = groupService.lambdaQuery()
                    .in(GalleryGroup::getGroupCode,parentCodes)
                    .list().stream()
                    .collect(Collectors.toMap(GalleryGroup::getGroupCode,v->v,(v1,v2)->v1));
        }
        // 查询子元素
        Set<String> groupCodes = list.stream()
                .map(GalleryGroup::getGroupCode)
                .collect(Collectors.toSet());
        List<GalleryGroup> childList = groupService.lambdaQuery()
                .in(GalleryGroup::getParentGroupCode, groupCodes)
                .list();
        Map<String, List<GalleryGroup>> childMap = new HashMap<>();
        if(CollUtil.isNotEmpty(childList)){
            childMap = childList.stream()
                    .collect(Collectors.groupingBy(GalleryGroup::getParentGroupCode));
        }
        // 查询关联记录
        List<GalleryGroupRecord> groupRecordList = groupRecordService.lambdaQuery()
                .in(GalleryGroupRecord::getGroupCode, groupCodes)
                .list();
        Map<String, List<GalleryGroupRecord>> groupRecordMap = new HashMap<>();
        if(CollUtil.isNotEmpty(groupRecordList)){
            groupRecordMap = groupRecordList.stream()
                    .collect(Collectors.groupingBy(GalleryGroupRecord::getGroupCode));
        }

        Map<String, GalleryGroup> finalMap = map;
        Map<String, List<GalleryGroup>> finalChildMap = childMap;
        Map<String, List<GalleryGroupRecord>> finalGroupRecordMap = groupRecordMap;
        return list.stream()
                .map(resp -> toViewResponse(resp,
                        finalMap.get(resp.getParentGroupCode()),
                        finalChildMap.get(resp.getGroupCode()),
                        finalGroupRecordMap.get(resp.getGroupCode())
                        ))
                .collect(Collectors.toList());
    }

    public GroupViewResponse toViewResponse(GalleryGroup galleryGroup,
                                            GalleryGroup parent,
                                            List<GalleryGroup> childList,
                                            List<GalleryGroupRecord> recordList) {
        GroupViewResponse response = BeanUtil.toBean(galleryGroup, GroupViewResponse.class);
        response.setParentGroupName(parent==null?null:parent.getName());
        response.setChildNum(CollUtil.isNotEmpty(childList)?childList.size():0);
        response.setRecordNum(CollUtil.isNotEmpty(recordList)?recordList.size():0);
        return response;
    }

    public GroupResponse toResponse(GalleryGroup galleryGroup) {
        return BeanUtil.toBean(galleryGroup,GroupResponse.class);
    }
}
