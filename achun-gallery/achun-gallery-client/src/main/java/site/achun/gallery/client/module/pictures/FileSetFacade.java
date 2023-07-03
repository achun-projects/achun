package site.achun.gallery.client.module.pictures;

import site.achun.gallery.client.module.pic_unit.response.PicUnitResponse;
import site.achun.support.api.response.Rsp;

/**
 * Description
 *
 * @Author: Heiffeng
 * @Date: 2022/4/25 15:20
 */
public interface FileSetFacade {

    /**
     * 根据编码查询分组
     * @param pictureGroupCode
     * @return
     */
    Rsp<PicUnitResponse> queryByCode(String setCode);


}
