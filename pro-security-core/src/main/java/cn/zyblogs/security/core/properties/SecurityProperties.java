package cn.zyblogs.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @Title: SecurityProperties.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO 最顶级的  一层一层封装  默认配置
 * @Author ZhangYB
 * @Version V1.0
 */
@ConfigurationProperties(prefix = "zyblogs.security")
@Getter
@Setter
public class SecurityProperties {

    private BrowserProperties browser = new BrowserProperties();

    private ValidateCodeProperties code = new ValidateCodeProperties();
}
