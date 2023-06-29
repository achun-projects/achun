package site.achun.video.client.module.album;

import site.achun.video.client.module.album.request.MoveAlbumRecordRequest;
import site.achun.video.client.module.pictures.request.QueryRecord;
import site.achun.video.client.module.pictures.response.PictureResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.Collection;
import java.util.List;

/**
 * 相册记录
 *
 * @Author: Heiffeng
 * @Date: 2022/4/14 21:42
 */
public interface AlbumRecordFacade {

    /**
     * 查询分页数据
     * @param queryRecord
     * @return
     */
    Rsp<RspPage<PictureResponse>> queryPage(QueryRecord queryRecord);

    /**
     *
     * @param albumCode
     * @param createPictureList
     * @return
     */
    Rsp<Void> createAlbumRecord(String albumCode, Collection<String> groupCodes);

    /**
     * 在相册中移动相册
     * @param request
     * @return
     */
    Rsp<Void> moveAlbumRecord(MoveAlbumRecordRequest request);


    /**
     * 随机查询一条记录
     * @param albumCode
     * @return
     */
    Rsp<PictureResponse> randomOneRecord(String albumCode);

    Rsp<List<PictureResponse>> randomOneFileSet(String albumCode);
}
