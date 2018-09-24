package cn.zyblogs.security.core.social.qq.api.connet;

import cn.zyblogs.security.core.social.qq.api.QQ;
import cn.zyblogs.security.core.social.qq.api.QQUserInfo;
import org.springframework.social.connect.ApiAdapter;
import org.springframework.social.connect.ConnectionValues;
import org.springframework.social.connect.UserProfile;

/**
 * @Title: QQAdapter.java
 * @Package cn.zyblogs.security.core.social.qq.api.connet
 * @Description: TODO 适配
 * @Author ZhangYB
 * @Version V1.0
 */
public class QQAdapter implements ApiAdapter<QQ> {

    @Override
    public boolean test(QQ qq) {
        return true;
    }

    @Override
    public void setConnectionValues(QQ api, ConnectionValues values) {
            // 设置ConnectionValues需要的数据
         QQUserInfo userInfo = api.getUserInfo();

         // 昵称
        values.setDisplayName(userInfo.getNickname());
         // 头像 40*40
        values.setImageUrl(userInfo.getFigureurl_qq_1());
         // null
        values.setProfileUrl(null);
        // openid
        values.setProviderUserId(userInfo.getOpenId());
    }

    /**
     * 绑定解绑
     * @param qq
     * @return
     */
    @Override
    public UserProfile fetchUserProfile(QQ qq) {
        return null;
    }

    @Override
    public void updateStatus(QQ qq, String s) {

    }
}
