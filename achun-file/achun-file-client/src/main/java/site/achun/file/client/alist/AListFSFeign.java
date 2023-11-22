package site.achun.file.client.alist;

import feign.Headers;
import feign.Param;
import feign.RequestLine;
import site.achun.file.client.alist.request.FSGet;
import site.achun.file.client.alist.request.Login;
import site.achun.file.client.alist.response.FSListResponse;
import site.achun.file.client.alist.response.FSResponse;
import site.achun.file.client.alist.response.Resp;

public interface AListFSFeign {


    // 获取某个文件/目录信息
    @RequestLine("POST /api/fs/get")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    Resp<FSResponse> get(@Param("token") String token, FSGet req);

    // 列出文件目录
    @RequestLine("POST /api/fs/list")
    @Headers({
            "Content-Type: application/json",
            "Authorization: {token}"
    })
    Resp<FSListResponse> list(@Param("token") String token, FSGet req);

}
