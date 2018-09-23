package cn.zyblogs.code;

import cn.zyblogs.security.core.validate.code.ImageCode;
import cn.zyblogs.security.core.validate.code.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Title: DemoImageCodeGenerator.java
 * @Package cn.zyblogs.code
 * @Description: TODO  覆盖它的逻辑必须实现一个它的接口
 *               TODO   以增量适应变化 不是修改原来的代码 而是新加入了代码
 *               TODO  在别人不修改你写的代码的情况下 能改变这个模块的业务逻辑
 * @Author ZhangYB
 * @Version V1.0
 */
@Component("imageCodeGenerator")
public class DemoImageCodeGenerator implements ValidateCodeGenerator {
    @Override
    public ImageCode generate(ServletWebRequest request) {
        System.out.println("更高级的图形验证码生成代码");
        return null;
    }
}
