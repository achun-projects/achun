package site.achun.video.client.module.album;


import jakarta.validation.constraints.NotNull;
import site.achun.video.client.module.album.request.AlbumCreateRequest;
import site.achun.video.client.module.album.request.AlbumUpdateRequest;
import site.achun.video.client.module.album.request.QueryAlbumPage;
import site.achun.video.client.module.album.response.AlbumResponse;
import site.achun.support.api.exception.RspException;
import site.achun.support.api.request.ReqPage;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.List;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2021/12/7 16:36
 */
public interface AlbumFacade {

    /**
     * 分页查询相册列表
     * @param userCode
     * @return
     */
    Rsp<RspPage<AlbumResponse>> page(QueryAlbumPage queryPage);

    /**
     * 查询相册列表
     * @param page 分页查询信息
     * @param userCode 用户编码
     * @param containDefaultAlbum 是否包含默认相册
     * @return
     */
    Rsp<List<AlbumResponse>> list(ReqPage page, String userCode, Boolean containDefaultAlbum);


    /**
     * 查询相册详情
     * @param albumCode
     * @return
     */
    Rsp<AlbumResponse> detail(@NotNull String albumCode, String userCode);

    /**
     * 创建相册
     * @param createRequest
     * @return
     */
    Rsp<AlbumResponse> create(AlbumCreateRequest createRequest) throws RspException;

    /**
     * 修改相册信息
     * @param updateRequest
     * @return
     */
    Rsp<AlbumResponse> updateAlbum(AlbumUpdateRequest updateRequest) throws RspException;


    /**
     *
     * @param albumCode
     * @return
     */
    Rsp<Void> updateRecordUTime(String albumCode);


    /**
     * 删除相册（为空的相册）
     * @param albumCode
     * @return
     */
    Rsp<Void> removeAlbum(String albumCode,String userCode);



}
