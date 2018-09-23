package cn.zyblogs.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Title: SecurityProperties.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@ConfigurationProperties(prefix = "zyblogs.security")
@Getter
@Setter
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

}
