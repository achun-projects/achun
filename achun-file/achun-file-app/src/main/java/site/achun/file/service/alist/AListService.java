package site.achun.file.service.alist;

import com.alibaba.fastjson2.JSON;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import site.achun.file.client.alist.AListAuthFeign;
import site.achun.file.client.alist.AListFSFeign;
import site.achun.file.client.alist.request.FSGet;
import site.achun.file.client.alist.request.Login;
import site.achun.file.client.alist.response.FSListResponse;
import site.achun.file.client.alist.response.FSResponse;
import site.achun.file.client.alist.response.Resp;
import site.achun.file.client.alist.response.TokenResponse;
import site.achun.support.api.feign.FeignUtil;

import java.util.function.BiFunction;

@Slf4j
@Service
@RequiredArgsConstructor
public class AListService {
    private final static String alist_host = "http://192.168.100.111:5244";
    private static String token = null;
    private final static String username = "admin";
    private final static String password = "yunbin";

    public FSResponse get(String path){
        FSGet req = new FSGet();
        req.setPath(path);
        return request(FeignUtil.get(AListFSFeign.class, alist_host)::get,req).getData();
    }

    public FSListResponse list(String path){
        FSGet req = new FSGet();
        req.setPath(path);
        return request(FeignUtil.get(AListFSFeign.class,alist_host)::list,req).getData();
    }
    public <T extends Resp,U> T request(BiFunction<String,U,T> function,U req){
        if(token == null) token = getToken();
        T resp = function.apply(token,req);
        if(resp.getCode() == 200){
            return resp;
        }else if(resp.getCode() == 401){
            token = getToken();
            resp = function.apply(token,req);
            if(resp.getCode() == 200){
                return resp;
            }
        }
        if(resp!=null){
            log.error("alist请求失败,code:{},meesage:{},req:{}",resp.getCode(),resp.getMessage(), JSON.toJSONString(req));
        }else{
            log.error("alist请求失败，返回为空");
        }
        throw new RuntimeException("alist请求失败");
    }


    private String getToken(){
        Login req = new Login();
        req.setUsername(username);
        req.setPassword(password);
        Resp<TokenResponse> resp = FeignUtil.get(AListAuthFeign.class, alist_host).queryPage(req);
        if(resp.getCode().equals(200)){
            return resp.getData().getToken();
        }else{
            log.info("alist登录失败，code:{},message:{}",resp.getCode(),resp.getMessage());
            throw new RuntimeException("alist登录失败");
        }
    }

}
