package cn.zyblogs.security.browser;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Size;

/**
 * @Title: MyUserDetailsService.java
 * @Package cn.zyblogs.security.browser
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Component
@Slf4j
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 注入dao对象 jpa mapper等

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        log.info("登陆用户名: " + username );

        // 根据用户名查找用户信息
//        return new User(username , "123456" ,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        // 根据查找到用户信息判断用户是否被冻结
        // password 注册的时候需要加密
        String password = passwordEncoder.encode("123456");
        log.info("数据库密码是: " + password);
        return new User(username , password, true , true,true,true,AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }
}
