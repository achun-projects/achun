package site.achun.gateway.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class TestController {

    @GetMapping("/gateway/test/hello")
    public String hello(String name){
        return "Hello "+name;
    }

    @GetMapping("/gateway/echo")
    public String echo(){
        return "stand by";
    }
}
