
package cn.zyblogs.security.core.properties;

import lombok.Getter;
import lombok.Setter;

/**
 * @Title: ImageCodeProperties.java
 * @Package cn.zyblogs.security.core.properties
 * @Description: TODO  默认配置
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class SmsCodeProperties {
	

	private int length = 6;

	/**
	 *  过期时间 60s
	 */
	private int expireIn = 60;
	private String url;
	

}
