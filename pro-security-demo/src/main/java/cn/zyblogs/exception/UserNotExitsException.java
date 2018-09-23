package cn.zyblogs.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Title: UserNotExitsException.java
 * @Package cn.zyblogs.exception
 * @Description: TODO 自定义异常处理
 * @Author ZhangYB
 * @Version V1.0
 */
public class UserNotExitsException extends RuntimeException{

   private static final long serialVersionUID = -7034897190745766939L;

   @Getter
   private String id;

   public UserNotExitsException(String id){
       super("user not exist");
       this.id = id;
   }
}
