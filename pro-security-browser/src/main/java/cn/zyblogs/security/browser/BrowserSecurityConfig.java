package cn.zyblogs.security.browser;

import cn.zyblogs.security.core.properties.SecurityProperties;
import cn.zyblogs.security.core.validate.code.ValidateCodeFilter;
import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;

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
     *  登陆请求页面跳转 不需要权限认证
     */
    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler zyblogsAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler zyblogsAuthenticationFailHandler;

    /**
     *  数据源信息
     * @return
     */
    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * 加密  每次加密都生成不一样的字符串
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 记住我功能
     * @param http
     * @throws Exception
     */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
        // 系统启动自动建这张数据表  可以写也可以不写
        // 第二次启动还会建表  建议不写
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        // 自定义ValidateCodeFilter
        ValidateCodeFilter validateCodeFilter = new ValidateCodeFilter();
        validateCodeFilter.setAuthenticationFailureHandler(zyblogsAuthenticationFailHandler);
        // 传入配置
        validateCodeFilter.setSecurityProperties(securityProperties);
        // 配置的路径放入集合urls set集合
//        validateCodeFilter.afterPropertiesSet();

        //添加自定义过滤器
        http.addFilterBefore(validateCodeFilter ,UsernamePasswordAuthenticationFilter.class)
        // 表单登陆
                .formLogin()
                // 自定义登陆页面
//                .loginPage("/zyblogs-signIn.html")
                .loginPage("/authentication/require")
                // 提交这个请求时获得用户名和密码
                .loginProcessingUrl("/authentication/form")
                // 登陆成功这个处理器处理
                .successHandler(zyblogsAuthenticationSuccessHandler)
                // 失败的处理器
                .failureHandler(zyblogsAuthenticationFailHandler)
                .and()
                // 记住我配置
                .rememberMe()
                .tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(securityProperties.getBrowser().getRemeberMeSeconds())
                .userDetailsService(userDetailsService)
                // 记住我配置结束
        // 网页弹窗登陆
//                http.httpBasic()
                .and()
                // 下面都是授权的配置
                .authorizeRequests()

                // 权限匹配 这个页面不需要身份认证  其它的需要
                .antMatchers("/authentication/require" , securityProperties.getBrowser().getLoginPage() ,"/code/image").permitAll()

                // 任何请求
                .anyRequest()
                // 都需要身份认证
                .authenticated()
                .and()
                // csrf 关闭
                .csrf().disable();

    }

}
