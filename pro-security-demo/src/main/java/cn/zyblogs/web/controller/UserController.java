package cn.zyblogs.web.controller;

import cn.zyblogs.dto.User;
import cn.zyblogs.dto.UserQueryCondition;
import cn.zyblogs.exception.UserNotExitsException;
import com.fasterxml.jackson.annotation.JsonView;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
@RequestMapping(value = "user")
public class UserController {

    /**
     * 当前用户详细信息 参数Authentication authentication  返回详细信息
     * 参数@AuthenticationPrincipal UserDetails userDetails  只返回用户信息
     * @param authentication
     * @return
     */
    @GetMapping(value = "me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails userDetails ){
        return userDetails;
    }

    @GetMapping()
    @JsonView(User.UserSimpleView.class)
    @ApiOperation(value = "用户查询服务")
    public List<User> query(UserQueryCondition condition ,@PageableDefault(page = 2 ,size = 17 ,sort = "username ,asc") Pageable pageable){

        System.out.println(condition.toString());
        System.out.println(pageable.toString());

        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());
        users.add(new User());
        return users;
    }

    /**
     *  value = "user/{id:\\d+}" id正则表达式只可以传输数字
     * @param id
     * @return
     */
    @GetMapping(value = "{id:\\d+}")
    @JsonView(User.UserDetailView.class)
    public User getInfo(@ApiParam(value = "用户id") @PathVariable(name = "id") String id){

        // 已被处理  拦截器afterCompletion null
//        throw new UserNotExitsException(id);

//        throw  new RuntimeException(id);

        System.err.println("进入getInfo服务");
        User user = new User();
        user.setUsername("tom");
        return user;
    }

    @PostMapping
    public User create(@Valid @RequestBody  User user , BindingResult errors){

        if (errors.hasErrors()){
            errors.getAllErrors().stream().forEach(objectError -> System.out.println(objectError.getDefaultMessage()));
        }

        System.out.println("收到参数:" + user.getId());
        System.out.println("收到参数:" + user.getUsername());
        System.out.println("收到参数:" + user.getPassword());
        System.out.println("收到参数:" + user.getBirthday());

        user.setId("1");
        return user;
    }

    @PutMapping(value = "{id:\\d+}")
    public User update(@Valid @RequestBody  User user , BindingResult errors){

        if (errors.hasErrors()) {
            errors.getAllErrors().stream().forEach(error ->{
//                FieldError fieldError = (FieldError)error;
//                String message = fieldError.getField() + " " +error.getDefaultMessage();
                System.out.println(error.getDefaultMessage());
            });
        }

        System.out.println("收到参数Id:" + user.getId());
        System.out.println("收到参数Username:" + user.getUsername());
        System.out.println("收到参数Password:" + user.getPassword());
        System.out.println("收到参数Birthday:" + user.getBirthday());

        user.setId("1");
        return user;
    }

    @DeleteMapping(value = "{id:\\d+}")
    public void delete(@PathVariable String id){
        System.out.println("delete id " + id +"的用户 ");
    }
}
