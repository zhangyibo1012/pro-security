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
public class ValidateCode {

    private String code;
    private LocalDateTime expireTime;

    /**
     * 是否过期
     * @return
     */
    public boolean isExpried(){
        // 当前时间在过期时间之后 过期
        return LocalDateTime.now().isAfter(expireTime);
    }

    public ValidateCode(String code, int expireTime) {
        this.code = code;
        // 60s过期  当前时间加上60s
        this.expireTime = LocalDateTime.now().plusSeconds(60) ;
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        this.code = code;
        this.expireTime = expireTime;
    }
}
