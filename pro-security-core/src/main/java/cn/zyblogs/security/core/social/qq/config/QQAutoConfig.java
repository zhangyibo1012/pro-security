package cn.zyblogs.security.core.social.qq.config;

import cn.zyblogs.security.core.properties.QQProperties;
import cn.zyblogs.security.core.properties.SecurityProperties;
import cn.zyblogs.security.core.social.qq.api.connet.QQConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.social.SocialAutoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.connect.ConnectionFactory;

/**
 * @Title: QQAutoConfig.java
 * @Package cn.zyblogs.security.core.social.qq.config
 * @Description: TODO extends SocialAutoConfigurerAdapter
 *             @ConditionalOnProperty  只有配置了appid才生效  不配不起作用
 * @Author ZhangYB
 * @Version V1.0
 */
@Configuration
@ConditionalOnProperty(prefix = "zyblogs.security.social.qq" , name = "app-id")
public class QQAutoConfig extends SocialAutoConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    protected ConnectionFactory<?> createConnectionFactory() {

        QQProperties qqConfig = securityProperties.getSocial().getQq();


        return new QQConnectionFactory(qqConfig.getProviderId(), qqConfig.getAppId(), qqConfig.getAppSecret());
    }
}
