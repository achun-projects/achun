package site.achun.video.app.service.gallery_group;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.achun.video.app.generator.domain.GalleryGroup;
import site.achun.video.app.generator.mapper.GalleryGroupRecordMapper;
import site.achun.video.app.generator.service.GalleryGroupRecordService;
import site.achun.video.app.generator.service.GalleryGroupService;
import site.achun.video.client.dto.GroupRecordView;
import site.achun.video.client.module.group.enums.GroupType;
import site.achun.video.client.module.group.view.CascaderView;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Description
 *
 * @Author: SunAo
 * @Date: 2022/1/17 0017 16:57
 **/
@Slf4j
@Service
public class MyGalleryGroupService {

    @Autowired
    private GalleryGroupService groupService;
    @Autowired
    private GalleryGroupRecordMapper groupRecordMapper;
    @Autowired
    private GalleryGroupRecordService groupRecordService;

    public List<GalleryGroup> select(GroupType type, String userCode){
        return groupService.lambdaQuery()
                .eq(GalleryGroup::getUserCode, userCode)
                .eq(GalleryGroup::getType, type.getType())
                .orderByDesc(GalleryGroup::getCtime)
                .list();
    }

    public List<CascaderView> queryGroupCascaderView(GroupType type, String userCode, boolean justGroup) {
        List<GalleryGroup> list = select(type, userCode);
        // 计算父类
        List<CascaderView> parentList = list.stream()
                .filter(group -> StrUtil.isEmpty(group.getParentGroupCode()))
                .map(group -> CascaderView.builder()
                        .value(group.getGroupCode())
                        .label(group.getName())
                        .build())
                .collect(Collectors.toList());
        parse(parentList,list);
        // 补充最底层节点
        if(!justGroup){
            List<GroupRecordView> recordViews =
                    GroupType.BOARD.equals(type)
                            ?
                    groupRecordMapper.selectBoardRecord(userCode)
                            :
                    groupRecordMapper.selectAlbumRecord(userCode);
            Map<String, List<GroupRecordView>> recordViewMap = recordViews.stream()
                    .collect(Collectors.groupingBy(GroupRecordView::getGroupCode));
            parentList.stream().forEach(view->supplyView(view,recordViewMap));
        }
        return parentList;
    }

    private void supplyView(CascaderView view,Map<String, List<GroupRecordView>> recordViewMap){
        if(CollUtil.isNotEmpty(view.getChildren())){
            view.getChildren().stream().forEach(subView->supplyView(subView,recordViewMap));
        }
        if(recordViewMap.containsKey(view.getValue())){
            List<CascaderView> children = recordViewMap.get(view.getValue()).stream()
                    .map(recordView -> CascaderView.builder()
                            .label(recordView.getName())
                            .value(recordView.getListCode())
                            .build())
                    .collect(Collectors.toList());
            if(CollUtil.isNotEmpty(view.getChildren())){
                view.getChildren().addAll(children);
            }else{
                view.setChildren(children);
            }
        }
    }

    private void parse(List<CascaderView> parentList,List<GalleryGroup> list){
        // 删除父类元素
        list.removeIf(group->parentList.stream()
                .anyMatch(p->p.getValue().equals(group.getGroupCode())));
        // 根据父类查找子类
        Map<String, List<GalleryGroup>> map = list.stream().collect(Collectors.groupingBy(GalleryGroup::getParentGroupCode));
        parentList.stream().forEach(view->{
            if(map.containsKey(view.getValue())){
                List<CascaderView> children = map.get(view.getValue()).stream()
                        .map(group -> CascaderView.builder()
                                .label(group.getName())
                                .value(group.getGroupCode())
                                .build())
                        .collect(Collectors.toList());
                parse(children, list);
                view.setChildren(children);
            }
        });
    }

}
