
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
public class ImageCodeProperties  extends SmsCodeProperties {

	public ImageCodeProperties(){
		setLength(4);
	}

	private int width = 67;
	private int height = 23;

}
