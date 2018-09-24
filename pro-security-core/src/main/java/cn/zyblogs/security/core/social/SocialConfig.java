package cn.zyblogs.security.core.social;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.config.annotation.SocialConfigurerAdapter;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.jdbc.JdbcUsersConnectionRepository;
import org.springframework.social.security.SpringSocialConfigurer;

import javax.sql.DataSource;

/**
 * @Title: SocialConfig.java
 * @Package cn.zyblogs.security.core.social
 * @Description: TODO 社交配置
 * @Author ZhangYB
 * @Version V1.0
 */
@Configuration
@EnableSocial
public class SocialConfig extends SocialConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {

        // 第三个参数加解密 Encryptors.noOpText() 不做任何操作
        JdbcUsersConnectionRepository repository = new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());

        //可以设置表的前缀
//        repository.setTablePrefix("zyblogs_");
        return repository;
    }

    @Bean
    public SpringSocialConfigurer zyblogsSocialSecurityConfig(){
        return new SpringSocialConfigurer();
    }
}
