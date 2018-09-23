package cn.zyblogs.security.core.properties;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: SecurityCoreConfig.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO 配置生效
 * @Author ZhangYB
 * @Version V1.0
 */
@Configuration
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityCoreConfig {
}
