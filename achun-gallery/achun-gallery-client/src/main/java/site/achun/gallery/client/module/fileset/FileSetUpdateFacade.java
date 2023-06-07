package site.achun.gallery.client.module.fileset;

import site.achun.gallery.client.module.fileset.request.AnewFileSet;
import site.achun.gallery.client.module.fileset.request.CreateFileSet;
import site.achun.gallery.client.module.fileset.request.CreateSetCode;
import site.achun.gallery.client.module.fileset.request.UpdateFileSet;
import site.achun.gallery.client.module.fileset.response.FileSetResponse;
import site.achun.gallery.client.module.pictures.request.UploadPicturesRequest;
import site.achun.support.api.response.Rsp;

/**
 * 文件分组更新接口
 * <p>
 * Author: Heiffeng
 * Date: 2023/3/20
 */
public interface FileSetUpdateFacade {

    /**
     *
     * @param anewFileset
     * @return
     */
    Rsp<FileSetResponse> anewFileset(AnewFileSet anewFileset);


    /**
     *
     * @param createFileSet
     * @return
     */
    Rsp<FileSetResponse> createFileSet(UploadPicturesRequest createFileSet);


    /**
     * 更改文件分组
     * @param update
     * @return
     */
    Rsp<FileSetResponse> update(UpdateFileSet update);

    /**
     *
     * @param update
     * @return
     */
    Rsp<FileSetResponse> createOrUpdate(CreateFileSet create);

    /**
     * 创建文件分组
     * @param request
     * @return
     */
    Rsp<FileSetResponse> create(CreateSetCode create);

}
