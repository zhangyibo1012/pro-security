package cn.zyblogs.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetailsService;

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
    private String username;

    @JsonView(UserDetailView.class)
    private String password;
}
