package site.achun.video.client.module.pictures;

import site.achun.video.client.module.fileset.response.FileSetResponse;
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
    Rsp<FileSetResponse> queryByCode(String setCode);


}
