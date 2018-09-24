package cn.zyblogs.security.core.validate.code;

import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Title: ValidateCodeGenerator.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO 验证码生成器
 * @Author ZhangYB
 * @Date 2018-09-23 23:28
 * @Version V1.0
 */
public interface ValidateCodeGenerator {

    ValidateCode generate(ServletWebRequest request);
}
