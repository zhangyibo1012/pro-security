package cn.zyblogs.security.browser;

import cn.zyblogs.security.browser.support.SimpleResponse;
import cn.zyblogs.security.core.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: BrowserSecurityController.java
 * @Package cn.zyblogs.security.browser
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
@Slf4j
public class BrowserSecurityController {

    /**
     * 缓存请求的url
     */
    private RequestCache requestCache = new HttpSessionRequestCache();

    /**
     * 跳转请求
     */
    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Autowired
    private SecurityProperties securityProperties;

    /**
     * 当需要认证的时候跳转到这里
     *
     * @param request
     * @param response HttpStatus.UNAUTHORIZED 401
     * @return
     */
    @RequestMapping(value = "authentication/require")
    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    public SimpleResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {

        SavedRequest savedRequest = requestCache.getRequest(request, response);
        if (savedRequest != null) {
            String targetUrl = savedRequest.getRedirectUrl();
            log.info("引发跳转的请求是: " + targetUrl);
            if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
                redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getLoginPage());
            }
        }
        return new SimpleResponse("访问的服务需要身份认证，请引导用户到登录页");
    }
}
