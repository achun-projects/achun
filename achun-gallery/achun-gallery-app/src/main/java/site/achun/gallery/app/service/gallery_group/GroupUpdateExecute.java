package site.achun.gallery.app.service.gallery_group;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.GalleryGroup;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.generator.service.GalleryGroupService;
import site.achun.gallery.client.constant.GalleryRC;
import site.achun.gallery.client.module.group.request.CreateGalleryGroup;
import site.achun.gallery.client.module.group.request.ReqRemoveEmptyGroup;
import site.achun.gallery.client.module.group.request.UpdateGroupReq;
import site.achun.support.api.exception.RspException;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 分组更新执行器
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupUpdateExecute {

    private final GalleryGroupService groupService;
    private final GalleryGroupRecordService groupRecordService;

    public void removeEmptyGroup(ReqRemoveEmptyGroup req){
        GalleryGroup group = groupService.queryByCode(req.getGroupCode(), req.getUserCode());
        if(group == null){
            throw new RspException(GalleryRC.NOT_EXISTS);
        }
        // 查询是否存在下级成员
        List<GalleryGroup> childList = groupService.lambdaQuery()
                .eq(GalleryGroup::getParentGroupCode, req.getGroupCode())
                .list();
        if(CollUtil.isNotEmpty(childList)){
            // 存在下级成员，无法删除
            throw new RspException(GalleryRC.EXISTS,"分组[%s]存在%s个下级成员，清空下级成员后，再次尝试删除。",req.getGroupCode(),childList.size()+"");
        }

        // 查询记录是否是空的
        List<GalleryGroupRecord> list = groupRecordService.lambdaQuery()
                .eq(GalleryGroupRecord::getGroupCode, req.getGroupCode())
                .list();
        if(CollUtil.isNotEmpty(list)){
            // 存在记录，不为空，无法删除
            throw new RspException(GalleryRC.EXISTS,"分组[%s]存在%s条记录，无法删除。",req.getGroupCode(),list.size()+"");
        }
        groupService.removeById(group);
    }

    public void updateGroup(UpdateGroupReq req){
        groupService.lambdaUpdate()
                .eq(GalleryGroup::getGroupCode,req.getGroupCode())
                .eq(StrUtil.isNotEmpty(req.getUserCode()),GalleryGroup::getUserCode,req.getUserCode())
                .set(StrUtil.isNotBlank(req.getName()),GalleryGroup::getName,req.getName())
                .set(StrUtil.isNotEmpty(req.getDescription()),GalleryGroup::getDescription,req.getDescription())
                .set(StrUtil.isNotEmpty(req.getParentGroupCode()),GalleryGroup::getParentGroupCode,req.getParentGroupCode())
                .set(GalleryGroup::getUtime, LocalDateTime.now())
                .update();
    }

    public void create(CreateGalleryGroup create){
        GalleryGroup group = BeanUtil.toBean(create,GalleryGroup.class);
        group.setCtime(LocalDateTime.now());
        group.setUtime(LocalDateTime.now());
        group.setRecordUtime(LocalDateTime.now());
        groupService.save(group);
    }

}
