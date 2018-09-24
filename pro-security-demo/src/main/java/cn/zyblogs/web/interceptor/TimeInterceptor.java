package cn.zyblogs.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title: TimeInterceptor.java
 * @Package cn.zyblogs.web.interceptor
 * @Description: TODO 拦截器 spring框架提供的 获取处理的类和方法 @Component交给容器
 * @Author ZhangYB
 * @Version V1.0
 */
@Component
public class TimeInterceptor implements HandlerInterceptor {

    /**
     * 在控制器Controller方法被调用之前调用
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) throws Exception {
        System.err.println("TimeInterceptor.preHandle");
        System.err.println(((HandlerMethod) handler).getBean().getClass().getName());
        System.err.println(((HandlerMethod) handler).getMethod().getName());
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        // true 放行
        return true;
    }

    /**
     * 在控制器Controller方法被调用之后调用  抛出异常不被调用
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.err.println("TimeInterceptor.postHandle");
        final long start = (long) httpServletRequest.getAttribute("startTime");
        System.err.println("time interceptor 耗时:" + (System.currentTimeMillis() - start));
    }

    /**
     * 无论是否异常 都会被调用执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) throws Exception {
        System.err.println("TimeInterceptor.afterCompletion");
        final long start = (long) httpServletRequest.getAttribute("startTime");
        System.err.println("time interceptor 耗时:" + (System.currentTimeMillis() - start));
        System.err.println("ex is " + ex);
    }
}
