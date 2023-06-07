package site.achun.gallery.client.module.fileset;

import site.achun.gallery.client.module.fileset.request.QueryFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.support.api.exception.RspException;
import site.achun.support.api.response.Rsp;

/**
 * Author: Heiffeng
 * Date: 2023/3/27
 */
public interface FileSetQueryFacade {

    /**
     * 查询文件分组
     * @param setCode
     * @return
     * @throws RspException
     */
    Rsp<FileSetResponse> query(QueryFileSet request) throws RspException;
}
