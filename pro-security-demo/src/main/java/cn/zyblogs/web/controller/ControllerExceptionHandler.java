package cn.zyblogs.web.controller;

import cn.zyblogs.exception.UserNotExitsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: ControllerExceptionHandler.java
 * @Package cn.zyblogs.web.controller
 * @Description: TODO
 * @Author ZhangYB
 * @Version V1.0
 */
@ControllerAdvice
public class ControllerExceptionHandler {

    /**
     * @param ex
     * @return
     * @ExceptionHandler(UserNotExitsException.class)遇到UserNotExitsException异常调用这个方法处理
     * @ResponseBody 转换为json的注解
     * @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 返回的状态码
     */
    @ExceptionHandler(UserNotExitsException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Map<String, Object> handleUserNotExistException(UserNotExitsException ex) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("id", ex.getId());
        result.put("message", ex.getMessage());
        return result;
    }

}
