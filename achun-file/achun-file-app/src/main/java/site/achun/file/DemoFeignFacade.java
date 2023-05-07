package site.achun.file;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("achun-provider")
public interface DemoFeignFacade {


    @GetMapping("/hello")
    public String sayHello();

}
