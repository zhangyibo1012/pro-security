package cn.zyblogs.security.core.social.qq.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.social.oauth2.AbstractOAuth2ApiBinding;
import org.springframework.social.oauth2.TokenStrategy;

import java.io.IOException;

/**
 * @Title: QQImpl.java
 * @Package cn.zyblogs.security.core.social.qq.api
 * @Description: TODO
 * @Author ZhangYB 所有api都继承这个AbstractOAuth2ApiBinding
 * @Version V1.0
 */
@Getter
@Setter
public  class QQImpl extends AbstractOAuth2ApiBinding implements QQ {


    private static final String URL_GRT_OPRNID = "https://graph.qq.com/oauth2.0/me?access_token=%s";

    private static final String URL_GET_USERINFO = "https://graph.qq.com/user/get_user_info?oauth_consumer_key=%s&openid=%s";

    private String appId;
    private String openId;

    /**
     *  字符串转换为对象
     */
    private ObjectMapper objectMapper = new ObjectMapper();

    public QQImpl(String accessToken ,String appId){
        super(accessToken ,TokenStrategy.ACCESS_TOKEN_PARAMETER);
        this.appId =appId;

        // 父类的方法
        // accessToken替换掉%s
        String url = String.format(URL_GRT_OPRNID , accessToken);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        // 读取openid
        this.openId = StringUtils.substringBetween(result , "\"openId\":" ,"}");
    }

    /**
     * 获取qq用户信息
     *
     * @return
     */
    @Override
    public QQUserInfo getUserInfo() {

        // %s替换成对应的参数
        String url = String.format(URL_GET_USERINFO ,appId ,openId);
        String result = getRestTemplate().getForObject(url, String.class);

        System.out.println(result);

        // 把字符串转换位QQUserInfo对象
        try {
            return objectMapper.readValue(result , QQUserInfo.class);
        } catch (IOException e) {
            throw new RuntimeException("获取用户信息失败");
        }
    }
}
