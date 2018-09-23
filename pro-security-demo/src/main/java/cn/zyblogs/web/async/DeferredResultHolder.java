package cn.zyblogs.web.async;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;

/**
 * @Title: DeferredResultHolder.java
 * @Package cn.zyblogs.web.async
 * @Description:  消息传递
 * @Author ZhangYB
 * @Version V1.0
 */
@Getter
@Setter
@Component
public class DeferredResultHolder {

    private Map<String, DeferredResult<String>> map = new HashMap<>();
}
