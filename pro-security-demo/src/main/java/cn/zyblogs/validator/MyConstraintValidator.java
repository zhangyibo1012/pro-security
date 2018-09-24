package cn.zyblogs.validator;

import cn.zyblogs.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Title: MyConstraintValidator.java
 * @Package cn.zyblogs.validator
 * @Description: TODO 自定义注解效验器 实现ConstraintValidator自动作为bean加入容器
 * @Author ZhangYB
 * @Version V1.0
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint, Object> {

    @Autowired
    private HelloService helloService;

    @Override
    public void initialize(MyConstraint myConstraint) {
        // 效验器初始化
        System.out.println("my validator init.");
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
        // 效验逻辑
        helloService.greeting("tom");
        System.out.println(value);

        // false 失败  true OK
        return false;
    }
}
