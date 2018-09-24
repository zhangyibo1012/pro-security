package cn.zyblogs.security.core.validate.code;

import cn.zyblogs.security.core.properties.SecurityProperties;
import cn.zyblogs.security.core.validate.code.image.ImageCodeGenerator;
import cn.zyblogs.security.core.validate.code.sms.DefaultSmsCodeSender;
import cn.zyblogs.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title: ValidateCodeBeanConfig.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO 配置类
 * @Author ZhangYB
 * @Version V1.0
 */
@Configuration
public class ValidateCodeBeanConfig {

    /**
     *  配置信息
     */
    @Autowired
    private SecurityProperties securityProperties;

    /**
     * @ConditionalOnMissingBean 这个注解在系统启动的时候加载这个bean之前会在spring容器中找一下是否已经有这个name的bean了,如果找到就不会用这个了
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator() {
        ImageCodeGenerator codeGenerator = new ImageCodeGenerator();
        codeGenerator.setSecurityProperties(securityProperties);
        return codeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(SmsCodeSender.class)
    public SmsCodeSender smsCodeSender(){
        return new DefaultSmsCodeSender();
    }
}
