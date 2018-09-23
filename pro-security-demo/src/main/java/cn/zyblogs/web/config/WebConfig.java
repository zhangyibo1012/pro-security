package cn.zyblogs.web.config;

import cn.zyblogs.web.filter.TimeFilter;
import cn.zyblogs.web.interceptor.TimeInterceptor;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Title: WebConfig.java
 * @Package cn.zyblogs.web.config
 *              过滤器不需要继承
 * @Description: TODO 拦截器配置继承WebMvcConfigurerAdapter 重写addInterceptors
 * @Author ZhangYB
 * @Version V1.0
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {

    @Autowired
    private TimeInterceptor timeInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 添加配置
        registry.addInterceptor(timeInterceptor);
    }

    /**
     *  注册TimeFilter过滤器 加入springboot
     * @return
     */
    @Bean
    public FilterRegistrationBean timeFilter(){

        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        TimeFilter timeFilter = new TimeFilter();
        registrationBean.setFilter(timeFilter);

        // 指定在那个路径上起作用
        List<String> urls = new ArrayList<>();

        // /* 所有路径
        urls.add("/*");

        registrationBean.setUrlPatterns(urls);

        return registrationBean;

    }
}
