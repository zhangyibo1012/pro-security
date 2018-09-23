package cn.zyblogs.security.core.validate.code;

import cn.zyblogs.security.core.properties.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @Title: ValidateCodeFilter.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO 处理效验验证码 OncePerRequestFilter工具类
 *                      保证过滤器只可以调用一次
 *
 *                      InitializingBean接口 初始化urls值
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    private AuthenticationFailureHandler authenticationFailureHandler;

    /**
     *  操作session工具类对象
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();

    private Set<String> urls = new HashSet<>();

    private SecurityProperties securityProperties;

    /**
     *  工具类  路径匹配器
     */
    private AntPathMatcher pathMatcher = new AntPathMatcher();

    /**
     *  覆盖afterPropertiesSet方法  添加路径
     * @throws ServletException
     */
    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();

        // 逗号分隔成数组String
        String[] configUrls = StringUtils
                .splitByWholeSeparatorPreserveAllTokens(securityProperties.getCode().getImage().getUrl() , ",");
        for (String configUrl : configUrls) {
            // 配置的路径 添加集合
            urls.add(configUrl);
        }

        // 登陆请求必须需要验证码 添加集合
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // 请求这个路径 并且是POST  tringUtils.equalsIgnoreCase 忽略大小写
//        if (StringUtils.equals("/authentication/form" ,request.getRequestURI()) && StringUtils.equalsIgnoreCase(request.getMethod(),"post"))
          {
            boolean action = false;
            for (String url :urls){
                // 匹配成功
                if (pathMatcher.match(url , request.getRequestURI())){
                    action = true;
                }
            }

            if (action){
                try {
                    // 效验 从session中拿数据
                    validate(new ServletWebRequest(request));
                }catch (ValidateCodeException ex){
                authenticationFailureHandler.onAuthenticationFailure(request , response ,ex);
                // 失败之后直接return
                    return;
                }
            }
        }

        filterChain.doFilter(request , response);
    }

    /**
     * 效验逻辑
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException, ValidateCodeException {

        // 从session中获取
        ImageCode codeInSession = (ImageCode)sessionStrategy.getAttribute(request ,ValidateCodeController.SESSION_KEY);

        // 从请求中获取imagecode
        String codeInRequest = ServletRequestUtils.getStringParameter(request.getRequest(), "imageCode");

        if (StringUtils.isBlank(codeInRequest)){
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession == null){
            throw new ValidateCodeException("验证码不存在");
        }

        // session中的验证码和用户上传的验证码 比较
        if (!StringUtils.equals(codeInSession.getCode() , codeInRequest)){
            throw new ValidateCodeException("验证码不匹配");
        }

        if (codeInSession.isExpried()){
            sessionStrategy.removeAttribute(request , ValidateCodeController.SESSION_KEY);
            throw new ValidateCodeException("验证码已过期");
        }

        // 清除session中的验证码
        sessionStrategy.removeAttribute(request , ValidateCodeController.SESSION_KEY);
    }
}
