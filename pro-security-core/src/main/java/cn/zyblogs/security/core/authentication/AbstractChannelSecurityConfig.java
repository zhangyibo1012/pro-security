package cn.zyblogs.security.core.authentication;

import cn.zyblogs.security.core.properties.SecurityConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @Title: AbstractChannelSecurityConfig.java
 * @Package cn.zyblogs.security.core.authentication
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class AbstractChannelSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    protected AuthenticationSuccessHandler zyblogsAuthenticationSuccessHandler;

    @Autowired
    protected AuthenticationFailureHandler zyblogsAuthenticationFailureHandler;

    protected void applyPasswordAuthenticationConfig(HttpSecurity http) throws Exception {
        http.formLogin()
                .loginPage(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
                .loginProcessingUrl(SecurityConstants.DEFAULT_LOGIN_PROCESSING_URL_FORM)
                .successHandler(zyblogsAuthenticationSuccessHandler)
                .failureHandler(zyblogsAuthenticationFailureHandler);
    }

}