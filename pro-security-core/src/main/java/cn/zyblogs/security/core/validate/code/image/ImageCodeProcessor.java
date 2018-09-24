package cn.zyblogs.security.core.validate.code.image;

import cn.zyblogs.security.core.validate.code.impl.AbstractValidateCodeProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;

/**
 * @Title: ImageCodeProcessor.java
 * @Package cn.zyblogs.security.core.validate.code.image
 * @Description: TODO 图片验证码处理器
 * @Author ZhangYB
 * @Version V1.0
 */
@Component("imageValidateCodeProcessor")
public class ImageCodeProcessor extends AbstractValidateCodeProcessor<ImageCode> {

    /**
     * 发送图形验证码，将其写到响应中
     */
    @Override
    protected void send(ServletWebRequest request, ImageCode imageCode) throws Exception {
        ImageIO.write(imageCode.getImage(), "JPEG", request.getResponse().getOutputStream());
    }

}
