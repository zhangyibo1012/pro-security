package cn.zyblogs.dto;

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
    private int age;
    private int ageTo;
    private String xxx;
}
