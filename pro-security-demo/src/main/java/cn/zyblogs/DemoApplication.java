package cn.zyblogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Title: DemoApplication.java
 * @Package cn.zyblogs
 * @Description: TODO  http://localhost:8080/swagger-ui.html
 * @Author ZhangYB
 * @Version V1.0
 */
@SpringBootApplication
@RestController
@EnableSwagger2
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class , args);
    }

    @GetMapping(value = "hello")
    public String hello(){
        return "Hello Spring Security";
    }
}
