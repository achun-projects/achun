package site.achun.file;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class AchunFileAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(AchunFileAppApplication.class, args);
    }


    @Autowired
    private DemoFeignFacade demoFeignFacade;
    @GetMapping("/hi")
    public String hi(){
        return demoFeignFacade.sayHello();
    }
}
