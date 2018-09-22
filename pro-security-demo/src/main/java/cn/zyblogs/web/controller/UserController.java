package cn.zyblogs.web.controller;

import cn.zyblogs.dto.User;
import cn.zyblogs.dto.UserQueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: UserController.java
 * @Package cn.zyblogs.web.controller
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
@Slf4j
public class UserController {

    @RequestMapping(value = "/user" , method = RequestMethod.GET)
    public List<User> query(UserQueryCondition condition ,@PageableDefault(page = 2 ,size = 17 ,sort = "username ,asc") Pageable pageable){

        System.out.println(condition.toString());
        System.out.println(pageable.toString());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }
}
