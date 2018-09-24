package cn.zyblogs.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Title: SocialProperties.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO 封装一层  把qq配置放在这里面
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class SocialProperties {

    private QQProperties qq = new QQProperties();
}
