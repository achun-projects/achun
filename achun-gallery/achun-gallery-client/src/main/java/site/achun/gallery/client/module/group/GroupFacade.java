package site.achun.gallery.client.module.group;

import site.achun.gallery.client.module.group.enums.GroupType;
import site.achun.gallery.client.module.group.request.*;
import site.achun.gallery.client.module.group.response.GroupResponse;
import site.achun.gallery.client.module.group.response.GroupViewResponse;
import site.achun.gallery.client.module.group.view.CascaderView;
import site.achun.support.api.exception.RspException;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/1/14 13:39
 */
public interface GroupFacade {

    /**
     *
     * @param type
     * @param userCode
     * @return
     */
    Rsp<List<GroupResponse>> list(GroupType type, String userCode);


    /**
     * 获取级联数据
     * @param type
     * @param userCode
     * @param justGroup
     * @return
     */
    Rsp<List<CascaderView>> queryGroupCascaderView(GroupType type, String userCode, boolean justGroup);


    /**
     * 查询分页
     * @param query
     * @return
     */
    Rsp<RspPage<GroupViewResponse>> queryPage(QueryPage query) throws RspException;;

    Rsp<GroupViewResponse> queryByCode(QueryGroupByCode req) throws RspException;;

    Rsp<Boolean> removeEmptyGroup(ReqRemoveEmptyGroup req) throws RspException;;

    Rsp<GroupViewResponse> updateByCode(UpdateGroupReq req) throws RspException;;

    /**
     *
     * @param create
     * @return
     */
    Rsp<GroupViewResponse> create(CreateGalleryGroup create) throws RspException;



}
