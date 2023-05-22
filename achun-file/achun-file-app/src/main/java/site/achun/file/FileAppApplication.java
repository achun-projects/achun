package site.achun.file;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("${spring.mapper.scan}")
@EnableFeignClients(basePackages = "site.achun")
@EnableAsync
public class FileAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileAppApplication.class, args);
    }


    @Autowired
    private DemoFeignFacade demoFeignFacade;
    @GetMapping("/hi")
    public String hi(){
        return demoFeignFacade.sayHello();
    }
}
