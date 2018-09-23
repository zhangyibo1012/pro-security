package cn.zyblogs.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Title: BrowserProperties.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class BrowserProperties {

    /**
     *  用户配了值就用户配置  用户未配置就是用默认
     */
    private String loginPage = "/zyblogs-signIn.html";

    /**
     *  默认返回json
     */
    private LoginType loginType = LoginType.JSON;

    /**
     *  一小时
     */
    private int remeberMeSeconds = 3600;
}
