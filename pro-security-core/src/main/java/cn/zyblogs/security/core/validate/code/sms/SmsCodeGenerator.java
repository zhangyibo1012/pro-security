package cn.zyblogs.security.core.validate.code.sms;

import cn.zyblogs.security.core.properties.SecurityProperties;
import cn.zyblogs.security.core.validate.code.ValidateCode;
import cn.zyblogs.security.core.validate.code.ValidateCodeGenerator;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Title: SmsCodeGenerator.java
 * @Package cn.zyblogs.security.core.validate.code.sms
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Component("smsValidateCodeGenerator")
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
        return new ValidateCode(code, securityProperties.getCode().getSms().getExpireIn());
    }




}

