package cn.zyblogs.security.core.social.qq.api.connet;

import cn.zyblogs.security.core.social.qq.api.QQ;
import cn.zyblogs.security.core.social.qq.api.QQImpl;
import org.springframework.social.oauth2.AbstractOAuth2ServiceProvider;
import org.springframework.social.oauth2.OAuth2Template;

/**
 * @Title: QQServiceProvider.java
 * @Package cn.zyblogs.security.core.social.qq.api.connet
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class QQServiceProvider extends AbstractOAuth2ServiceProvider<QQ> {

    private String appId;

    private static final String URL_AUTHORIZE = "https://graph.qq.com/oauth2.0/authorize";

    private static final String URL_ACCESS_TOKEN = "https://graph.qq.com/oauth2.0/token";


    public QQServiceProvider(String appId , String appSecret) {
        super(new OAuth2Template(appId ,appSecret ,URL_AUTHORIZE ,URL_ACCESS_TOKEN));
    }

    @Override
    public QQ getApi(String accessToken) {
        return new QQImpl(accessToken ,appId);
    }
}
