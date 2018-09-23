package cn.zyblogs.security.core.validate.code;


/**
 * @Title: ValidateCodeException.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
public class ValidateCodeException extends org.springframework.security.core.AuthenticationException {

    private static final long serialVersionUID = -7285211528095468156L;

    public ValidateCodeException(String msg) {
        super(msg);
    }
}
