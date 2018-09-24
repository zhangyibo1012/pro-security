package cn.zyblogs.security.core.validate.code.sms;

import cn.zyblogs.security.core.properties.SecurityConstants;
import cn.zyblogs.security.core.validate.code.ValidateCode;
import cn.zyblogs.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Title: SmsCodeProcessor.java
 * @Package cn.zyblogs.security.core.validate.code.sms
 * @Description: TODO 短信验证码处理器
 * @Author ZhangYB
 * @Version V1.0
 */
@Component("smsValidateCodeProcessor")
public class SmsCodeProcessor extends AbstractValidateCodeProcessor<ValidateCode> {

    /**
     * 短信验证码发送器
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @Override
    protected void send(ServletWebRequest request, ValidateCode validateCode) throws Exception {
        String paramName = SecurityConstants.DEFAULT_PARAMETER_NAME_MOBILE;
        String mobile = ServletRequestUtils.getRequiredStringParameter(request.getRequest(), paramName);
        smsCodeSender.send(mobile, validateCode.getCode());
    }

}

