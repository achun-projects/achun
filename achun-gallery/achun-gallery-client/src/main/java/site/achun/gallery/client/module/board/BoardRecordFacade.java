package site.achun.gallery.client.module.board;



import site.achun.gallery.client.module.pictures.request.QueryRecord;
import site.achun.gallery.client.module.pictures.response.PictureResponse;
import site.achun.support.api.response.Rsp;
import site.achun.support.api.response.RspPage;

import java.util.Collection;

/**
 * 画板记录
 *
 * @Author: Heiffeng
 * @Date: 2022/4/14 21:42
 */
public interface BoardRecordFacade {

    /**
     * 查询画板记录信息
     * @param queryRecord
     * @return
     */
    Rsp<RspPage<PictureResponse>> queryPage(QueryRecord queryRecord);

    /**
     * 在指定画板中随机查询一条记录
     * @param boardCode
     * @return
     */
    Rsp<PictureResponse> randomOneRecord(String boardCode);

    /**
     * 创建画板记录
     * @param boardCode
     * @param fileCodes
     * @return
     */
    Rsp<Void> createAlbumRecord(String boardCode, Collection<String> fileCodes);

    /**
     * 删除画板记录
     * @param boardCode
     * @param fileCodes
     * @return
     */
    Rsp<Void> removeAlbumRecord(String boardCode, Collection<String> fileCodes);
}
