package cn.zyblogs.security.browser;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Title: BrowserSecurityConfig.java
 * @Package cn.zyblogs.security.browser
 * @Description: TODO 网络安全适配器
 * @Author ZhangYB
 * @Version V1.0
 */
@Configuration
public class BrowserSecurityConfig  extends WebSecurityConfigurerAdapter {

    /**
     * 加密  每次加密都生成不一样的字符串
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 表单登陆
        http.formLogin()

        // 网页弹窗登陆
//                http.httpBasic()
                .and()
                // 下面都是授权的配置
                .authorizeRequests()
                // 任何请求
                .anyRequest()
                // 都需要身份认证
                .authenticated();


    }

}
