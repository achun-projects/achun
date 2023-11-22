package site.achun.file.client.alist;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import site.achun.file.client.alist.request.FSGet;
import site.achun.file.client.alist.request.Login;
import site.achun.file.client.alist.response.FSListResponse;
import site.achun.file.client.alist.response.FSResponse;
import site.achun.file.client.alist.response.Resp;
import site.achun.file.client.alist.response.TokenResponse;
import site.achun.support.api.feign.FeignUtil;

public class AListAuthFeignTest {

    private final static String alist_host = "http://192.168.100.111:5244";
    private final static String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VybmFtZSI6ImFkbWluIiwiZXhwIjoxNzAwNzA3MTMyLCJuYmYiOjE3MDA1MzQzMzIsImlhdCI6MTcwMDUzNDMzMn0.8EOcYQeMSN6nMJrbPwO8HDPa-r69PTvrVP8uMPgtVGU";
    @Test
    public void testLogin(){
        Login req = new Login();
        req.setUsername("admin2");
        req.setPassword("yunbin");
        Resp<TokenResponse> resp = FeignUtil.get(AListAuthFeign.class, alist_host).queryPage(req);
        System.out.println(JSON.toJSONString(resp));
    }

    @Test
    public void get(){
        FSGet req = new FSGet();
        req.setPath("/115/CutCut/20221200/VID20221209192041.mp4");
        Resp<FSResponse> resp = FeignUtil.get(AListFSFeign.class, alist_host).get(token, req);
        System.out.println(JSON.toJSONString(resp));
    }

    @Test
    public void list(){
        FSGet req = new FSGet();
        req.setPath("/115/CutCut/20221200");
        Resp<FSListResponse> resp = FeignUtil.get(AListFSFeign.class, alist_host).list(token, req);
        System.out.println(JSON.toJSONString(resp));
    }

}
