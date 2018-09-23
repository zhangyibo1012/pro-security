package cn.zyblogs.dto;

import cn.zyblogs.validator.MyConstraint;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.validation.constraints.Past;
import java.util.Date;

/**
 * @Title: User.java
 * @Package cn.zyblogs.dto
 * @Description: TODO dto输入输出数据
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
public class User {

    public interface UserSimpleView{}
    public interface  UserDetailView extends UserSimpleView{}

    @JsonView(UserSimpleView.class)
    private String id;

    @JsonView(UserSimpleView.class)
    @MyConstraint(message = "失败消息:这是一个测试自定义注解用例")
    private String username;

    @JsonView(UserDetailView.class)
    /**
     *  @NotBlank   password 不可以为空
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /**
     * @Past 必须是过去的时间
     */
    @JsonView(UserSimpleView.class)
    @Past(message = "生日必须是过去的时间")
    private Date birthday;
}
