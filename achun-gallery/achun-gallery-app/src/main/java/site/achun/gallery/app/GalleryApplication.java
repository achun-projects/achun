package site.achun.gallery.app;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;

@SpringBootApplication
@MapperScan("${spring.mapper.scan}")
@ComponentScan("site.achun")
@EnableDubbo
public class GalleryApplication {

    public static void main(String[] args) {
        SpringApplication.run(GalleryApplication.class, args);
    }

    @Bean
    @Primary
    public OpenAPI openAPIConfiguration() {
        return new OpenAPI().info(new Info()
                .title("相册系统API")
                .version("5.0")
                .description( "相册系统，可图片，可短视频。")
        );
    }
}
