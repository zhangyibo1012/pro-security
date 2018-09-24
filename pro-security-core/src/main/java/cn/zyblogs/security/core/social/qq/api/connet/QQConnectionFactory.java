package cn.zyblogs.security.core.social.qq.api.connet;

import cn.zyblogs.security.core.social.qq.api.QQ;
import org.springframework.social.connect.support.OAuth2ConnectionFactory;

/**
 * @Title: QQConnectionFactory.java
 * @Package cn.zyblogs.security.core.social.qq.api.connet
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class QQConnectionFactory extends OAuth2ConnectionFactory<QQ> {

    public QQConnectionFactory(String providerId, String appId , String appSecret) {
        super(providerId, new QQServiceProvider(appId ,appSecret), new QQAdapter());
    }
}
