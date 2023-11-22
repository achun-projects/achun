package site.achun.file.service.alist;

import com.alibaba.fastjson2.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import site.achun.file.client.alist.response.FSListResponse;
import site.achun.file.client.alist.response.FSResponse;

@SpringBootTest
public class AListServiceTest {

    @Autowired
    private AListService aListService;
    @Test
    public void get(){
        FSResponse resp = aListService.get("/115");
        System.out.println(JSON.toJSONString(resp));
    }

    @Test
    public void list(){
        FSListResponse resp = aListService.list("115/我的移动网盘/创蓝");
        System.out.println(JSON.toJSONString(resp));
    }
}
