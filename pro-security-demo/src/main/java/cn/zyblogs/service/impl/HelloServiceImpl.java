package cn.zyblogs.service.impl;

import cn.zyblogs.service.HelloService;
import org.springframework.stereotype.Service;

/**
 * @Title: HelloServiceImpl.java
 * @Package cn.zyblogs.service.impl
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    public String greeting(String name) {
        System.out.println("HelloServiceImpl.greeting");
        return "hello " + name;
    }
}
