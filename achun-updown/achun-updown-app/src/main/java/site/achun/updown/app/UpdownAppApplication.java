package site.achun.updown.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "site.achun")
@SpringBootApplication
public class UpdownAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(UpdownAppApplication.class, args);
    }

}
