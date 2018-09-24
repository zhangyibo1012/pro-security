package cn.zyblogs.security.browser.authentication;

import cn.zyblogs.security.core.properties.LoginType;
import cn.zyblogs.security.core.properties.SecurityProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: ZyblogsAuthenticationSuccessHandler.java
 * @Package cn.zyblogs.security.browser.authentication
 * @Description: TODO 自定义登陆成功会被调用该方法
 * @Author ZhangYB
 * @Version V1.0
 */
@Component("zyblogsAuthenticationSuccessHandler")
@Slf4j
public class ZyblogsAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    /**
     * authentication转换为json
     */
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        log.info("登陆成功");

        if (LoginType.JSON.equals(securityProperties.getBrowser().getLoginType())) {
            response.setContentType("application/json;charset=UTF-8");
            response.getWriter().write(objectMapper.writeValueAsString(authentication));
        } else {
            super.onAuthenticationSuccess(httpServletRequest, response, authentication);
        }


    }
}
