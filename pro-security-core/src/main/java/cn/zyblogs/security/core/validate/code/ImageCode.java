package cn.zyblogs.security.core.validate.code;

import lombok.Getter;
import lombok.Setter;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @Title: ImageCode.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class ImageCode extends ValidateCode{

    private BufferedImage image;


    public ImageCode(BufferedImage image, String code, int expireIn) {
        super(code,expireIn);
        this.image = image;
    }

    public ImageCode(BufferedImage image, String code, LocalDateTime expireTime) {
        super(code,expireTime);
        this.image = image;
    }
}
