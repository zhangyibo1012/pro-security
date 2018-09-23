package cn.zyblogs.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @Title: TimeAspect.java
 * @Package cn.zyblogs.web.aspect
 * @Description: TODO 切片
 *                  切入点:注解  在那些方法上起作用  在什么时候起作用
 *                  增强方法: 起作用时执行的业务逻辑
 *
 *                   @Around 切片使用这个注解
 * @Author ZhangYB
 * @Version V1.0
 */
@Aspect
@Component
public class TimeAspect {

    /**
     * execution(* cn.zyblogs.web.controller.UserController.*(..))
     *   * 任何返回值
     *   .* 任何方法
     *   （..） 任何参数
     *   起作用
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* cn.zyblogs.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");

        Object[] args = pjp.getArgs();
        for (Object arg : args) {
            System.out.println("arg is "+arg);
        }

        long start =System.currentTimeMillis();
        Object object = pjp.proceed();
        System.out.println("time aspect 耗时:"+ (System.currentTimeMillis() - start));
        System.out.println("time aspect end");
        return object;
    }
}
