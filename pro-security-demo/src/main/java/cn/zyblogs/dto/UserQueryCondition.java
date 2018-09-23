package cn.zyblogs.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @Title: UserQueryCondition.java
 * @Package cn.zyblogs.dto
 * @Description: TODO 查询条件
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
@ToString
public class UserQueryCondition {

    private String username;

    @ApiModelProperty(value = "用户年龄起始值")
    private int age;

    @ApiModelProperty(value = "用户年龄终止值")
    private int ageTo;
    private String xxx;
}
