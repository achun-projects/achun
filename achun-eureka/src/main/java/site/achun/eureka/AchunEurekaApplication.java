package site.achun.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AchunEurekaApplication {

	public static void main(String[] args) {
		SpringApplication.run(AchunEurekaApplication.class, args);
	}

}
