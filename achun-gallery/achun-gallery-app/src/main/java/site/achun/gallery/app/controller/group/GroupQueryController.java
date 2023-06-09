package site.achun.gallery.app.controller.group;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import site.achun.gallery.app.generator.domain.GalleryGroup;
import site.achun.gallery.app.service.gallery_group.GroupConvertExecute;
import site.achun.gallery.app.service.gallery_group.GroupQueryExecute;
import site.achun.gallery.app.service.gallery_group.MyGalleryGroupService;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.group.GroupQueryClient;
import site.achun.gallery.client.module.group.enums.GroupType;
import site.achun.gallery.client.module.group.request.QueryGroupByCode;
import site.achun.gallery.client.module.group.request.QueryPage;
import site.achun.gallery.client.module.group.response.GroupResponse;
import site.achun.gallery.client.module.group.response.GroupViewResponse;
import site.achun.gallery.client.module.group.view.CascaderView;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Tag(name = "分组查询")
@RestController
@RequiredArgsConstructor
public class GroupQueryController implements GroupQueryClient {

    private final GroupQueryExecute groupQueryExecute;
    private final MyGalleryGroupService myGalleryGroupService;
    private final GroupConvertExecute groupConvertExecute;

    @Override
    public Rsp<List<GroupResponse>> queryList(Integer type) {
        if(type == null || GroupType.parse(type) == null){
            return Rsp.error("type值不正确,type:"+type);
        }
        String userCode = UserInfo.getCode();
        List<GalleryGroup> groupList = myGalleryGroupService.select(GroupType.parse(type), userCode);
        return Rsp.success(groupList.stream()
                .map(groupConvertExecute::toResponse)
                .collect(Collectors.toList()));
    }

    @Override
    public Rsp<GroupViewResponse> queryByCode(String code) {
        QueryGroupByCode req = QueryGroupByCode.builder()
                .groupCode(code)
                .userCode(UserInfo.getCode())
                .build();
        return Rsp.success(groupQueryExecute.queryByCode(req));
    }

    @Override
    public Rsp<RspPage<GroupViewResponse>> queryPage(QueryPage query) {
        query.setUserCode(UserInfo.getCode(query::getUserCode));
        return Rsp.success(groupQueryExecute.queryPage(query));
    }

    @Override
    public Rsp<List<CascaderView>> queryCascaderView(Integer type, String currentSelect, Boolean justGroup) {
        String userCode = UserInfo.getCode();
        return Rsp.success(myGalleryGroupService.queryGroupCascaderView(GroupType.parse(type),userCode,justGroup));
    }

}
