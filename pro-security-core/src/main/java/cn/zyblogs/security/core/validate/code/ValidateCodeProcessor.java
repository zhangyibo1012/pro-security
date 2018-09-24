/**
 * 
 */
package cn.zyblogs.security.core.validate.code;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

/**
 * @Title: ValidateCodeGenerator.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO 校验码处理器，封装不同校验码的处理逻辑
 * @Author ZhangYB
 * @Date 2018-09-23 23:28
 * @Version V1.0
 */
public interface ValidateCodeProcessor {

	/**
	 * 验证码放入session时的前缀
	 */
	String SESSION_KEY_PREFIX = "SESSION_KEY_FOR_CODE_";

	/**
	 * 创建校验码
	 * 
	 * @param request
	 * @throws Exception
	 */
	void create(ServletWebRequest request) throws Exception;

	/**
	 * 校验验证码
	 * 
	 * @param servletWebRequest
	 * @throws Exception
	 */
	void validate(ServletWebRequest servletWebRequest);

}
