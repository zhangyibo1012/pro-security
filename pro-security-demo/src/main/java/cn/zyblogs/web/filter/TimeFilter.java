package cn.zyblogs.web.filter;



import javax.servlet.*;
import java.io.IOException;

/**
 * @Title: TimeFilter.java
 * @Package cn.zyblogs.web.filter
 * @Description: TODO 记录访问请求的时间 过滤器拦截器 @Component加入容器 生效
 * @Author ZhangYB
 * @Version V1.0
 */
//@Component
public class TimeFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.err.println("TimeFilter.init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.err.println("time filter start");
        long start = System.currentTimeMillis();
        chain.doFilter(request, response);
        System.err.println("time filter 耗时:"+ (System.currentTimeMillis() - start));
        System.err.println("time filter finish");
    }

    @Override
    public void destroy() {
        System.err.println("TimeFilter.destroy");
    }
}
