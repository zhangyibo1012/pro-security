package cn.zyblogs.security.core.validate.code.sms;

/**
 * @Title: DefaultSmsCodeSender.java
 * @Package cn.zyblogs.security.core.validate.code.sms
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class DefaultSmsCodeSender implements SmsCodeSender {
    @Override
    public void send(String mobile, String code) {
        System.out.println("向手机" + mobile + "发送短信验证码" + code);
    }
}
