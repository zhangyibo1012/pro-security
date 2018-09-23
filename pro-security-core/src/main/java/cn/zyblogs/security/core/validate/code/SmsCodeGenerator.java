package cn.zyblogs.security.core.validate.code;

import cn.zyblogs.security.core.properties.SecurityProperties;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @Title: ImageCodeGenerator.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO 短信验证码
 * @Author ZhangYB
 * @Version V1.0
 */
@Component("smsCodeGenerator")
public class SmsCodeGenerator implements ValidateCodeGenerator {

    /**
     *  配置信息
     */
    @Autowired
    @Getter
    @Setter
    private SecurityProperties securityProperties;


    @Override
    public ValidateCode generate(ServletWebRequest request) {
        String code = RandomStringUtils.randomNumeric(securityProperties.getCode().getSms().getLength());
        return new ValidateCode(code , securityProperties.getCode().getSms().getExpireIn());
    }
}
