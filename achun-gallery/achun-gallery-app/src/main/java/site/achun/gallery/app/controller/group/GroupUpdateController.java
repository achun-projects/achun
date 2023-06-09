package site.achun.gallery.app.controller.group;

import cn.hutool.core.util.StrUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import site.achun.gallery.app.service.gallery_group.GroupQueryExecute;
import site.achun.gallery.app.service.gallery_group.GroupUpdateExecute;
import site.achun.gallery.app.utils.UserInfo;
import site.achun.gallery.client.module.group.GroupUpdateClient;
import site.achun.gallery.client.module.group.request.CreateGalleryGroup;
import site.achun.gallery.client.module.group.request.QueryGroupByCode;
import site.achun.gallery.client.module.group.request.ReqRemoveEmptyGroup;
import site.achun.gallery.client.module.group.request.UpdateGroupReq;
import site.achun.gallery.client.module.group.response.GroupViewResponse;
import site.achun.support.api.response.Rsp;

import java.util.UUID;

@Slf4j
@Tag(name = "分组更新")
@RestController
@RequiredArgsConstructor
public class GroupUpdateController implements GroupUpdateClient {

    private final GroupUpdateExecute groupUpdateExecute;
    private final GroupQueryExecute groupQueryExecute;

    @Override
    public Rsp<GroupViewResponse> createGroup(Integer type, CreateGalleryGroup create) {
        String userCode = UserInfo.getCode();
        create.setType(type);
        create.setUserCode(userCode);
        if(StrUtil.isEmpty(create.getGroupCode())){
            create.setGroupCode(UUID.randomUUID().toString().replace("-",""));
        }
        groupUpdateExecute.create(create);
        return Rsp.success(groupQueryExecute.queryByCode(QueryGroupByCode.builder().groupCode(create.getGroupCode()).build()));
    }

    @Override
    public Rsp<GroupViewResponse> updateGroup(UpdateGroupReq req) {
        req.setUserCode(UserInfo.getCode(req::getUserCode));
        groupUpdateExecute.updateGroup(req);
        return Rsp.success(groupQueryExecute.queryByCode(QueryGroupByCode.builder().groupCode(req.getGroupCode()).build()));
    }

    @Override
    public Rsp<Boolean> removeEmptyGroup(ReqRemoveEmptyGroup req) {
        req.setUserCode(UserInfo.getCode(req::getUserCode));
        groupUpdateExecute.removeEmptyGroup(req);
        return Rsp.success(true);
    }
}
