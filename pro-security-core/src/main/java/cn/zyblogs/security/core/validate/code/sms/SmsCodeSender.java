package cn.zyblogs.security.core.validate.code.sms;

/**
 * @Title: SmsCodeSender.java
 * @Package cn.zyblogs.security.core.validate.code.sms
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public interface SmsCodeSender {

    void send(String mobile, String code);
}
