package site.achun.gallery.app;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan("${spring.mapper.scan}")
@EnableFeignClients(basePackages = "site.achun")
@EnableAsync
public class GalleryAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryAppApplication.class, args);
        log.info("GalleryAppApplication start...");
    }

}
