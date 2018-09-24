package cn.zyblogs.security.core.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.autoconfigure.social.SocialProperties;

/**
 * @Title: QQProperties.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO QQ配置
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class QQProperties extends SocialProperties {

    /**
     * 路径
      */
    private String providerId = "qq";
}
