package site.achun.updown.app;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@MapperScan("${spring.mapper.scan}")
@ComponentScan("site.achun")
@EnableDubbo
public class UpdownApplication {

    public static void main(String[] args) {
        System.out.println("my args:"+args[0]);
        SpringApplication.run(UpdownApplication.class, args);
    }

}
