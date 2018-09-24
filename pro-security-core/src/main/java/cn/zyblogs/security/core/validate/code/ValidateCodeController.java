package cn.zyblogs.security.core.validate.code;

import cn.zyblogs.security.core.validate.code.sms.SmsCodeSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Title: ValidateCodeController.java
 * @Package cn.zyblogs.security.core.validate.code
 * @Description: TODO  1.根据随机数生成图片
 *                      2.把随机数存入session
 *                      3.将生成的图片写到接口的响应
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
public class ValidateCodeController {


    /**
     *  操作session工具类对象
     */
    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();


    /**
     * 图片验证码
     */
    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    /**
     * 短信验证码
     */
    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    /**
     *  发送
     */
    @Autowired
    private SmsCodeSender smsCodeSender;

    @GetMapping(value = "code/image")
    public void createCode(HttpServletRequest request , HttpServletResponse response) throws IOException {

        ImageCode imageCode = (ImageCode) imageCodeGenerator .generate(new ServletWebRequest(request));
        //存入session 参数1：从请求中拿到session。 2.key  3.ImageCode
        sessionStrategy.setAttribute(new ServletWebRequest(request) ,ValidateCodeProcessor.SESSION_KEY_PREFIX + "IMAGE" ,imageCode );

        // 写出去的图片 格式 响应的输出流
        ImageIO.write(imageCode.getImage() , "JPEG" ,response.getOutputStream());
    }

    @GetMapping(value = "code/sms")
    public void createSmsCode(HttpServletRequest request , HttpServletResponse response) throws IOException, ServletRequestBindingException {

        ValidateCode smsCode = imageCodeGenerator .generate(new ServletWebRequest(request));
        //存入session 参数1：从请求中拿到session。 2.key  3.ImageCode
        sessionStrategy.setAttribute(new ServletWebRequest(request) ,ValidateCodeProcessor.SESSION_KEY_PREFIX + "SMS" ,smsCode );

        // 发送
        // 获取请求中的手机号参数
        String mobile = ServletRequestUtils.getRequiredStringParameter(request ,"mobile");
        smsCodeSender.send(mobile , smsCode.getCode());

    }
}
