package site.achun.file.client.alist;

import feign.Headers;
import feign.RequestLine;
import site.achun.file.client.alist.request.Login;
import site.achun.file.client.alist.response.Resp;
import site.achun.file.client.alist.response.TokenResponse;

public interface AListAuthFeign {

    @RequestLine("POST /api/auth/login")
    @Headers("Content-Type: application/json")
    Resp<TokenResponse> queryPage(Login req);

}
