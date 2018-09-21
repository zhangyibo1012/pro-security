package cn.zyblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Title: DemoApplication.java
 * @Package cn.zyblogs
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@SpringBootApplication
@RestController
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class , args);
    }

    @GetMapping(value = "hello")
    public String hello(){
        return "Hello Spring Security";
    }
}
