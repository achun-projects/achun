package site.achun.gallery.app.service.gallery_group;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.gallery.app.generator.domain.GalleryGroup;
import site.achun.gallery.app.generator.domain.GalleryGroupRecord;
import site.achun.gallery.app.generator.service.GalleryGroupRecordService;
import site.achun.gallery.app.generator.service.GalleryGroupService;
import site.achun.gallery.app.utils.PageUtil;
import site.achun.gallery.client.module.group.request.QueryGroupByCode;
import site.achun.gallery.client.module.group.request.QueryPage;
import site.achun.gallery.client.module.group.response.GroupViewResponse;
import site.achun.support.api.response.RspPage;

import java.util.List;

/**
 * 分组查询执行器
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/8
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GroupQueryExecute {
    private final GalleryGroupService groupService;
    private final GalleryGroupRecordService groupRecordService;

    private final GroupConvertExecute convertExecute;

    public RspPage<GroupViewResponse> queryPage(QueryPage query){
        Page<GalleryGroup> pageResult = groupService.lambdaQuery()
                .eq(StrUtil.isNotEmpty(query.getUserCode()), GalleryGroup::getUserCode, query.getUserCode())
                .eq(query.getType() != null, GalleryGroup::getType, query.getType())
                .page(Page.of(query.getReqPage().getPage(), query.getReqPage().getSize()));
        return PageUtil.batchParse(pageResult,query.getReqPage(),convertExecute::batchConvert);
    }


    public GroupViewResponse queryByCode(QueryGroupByCode req){
        GalleryGroup group = groupService.queryByCode(req.getGroupCode(),req.getUserCode());
        GalleryGroup parent = null;
        if(StrUtil.isNotEmpty(group.getParentGroupCode())){
            parent = groupService.queryByCode(group.getParentGroupCode());
        }
        List<GalleryGroup> childList = groupService.lambdaQuery()
                .eq(GalleryGroup::getParentGroupCode, group.getGroupCode())
                .list();
        List<GalleryGroupRecord> recordList = groupRecordService.lambdaQuery()
                .eq(GalleryGroupRecord::getGroupCode, group.getGroupCode())
                .list();
        return convertExecute.toViewResponse(group,parent,childList,recordList);
    }


}
