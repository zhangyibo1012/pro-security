/**
 *
 */
package cn.zyblogs.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @Title: AsyncController.java
 * @Package cn.zyblogs.web.async
 * @Description: TODO 异步处理请求
 * @Author ZhangYB
 * @Version V1.0
 */
@RestController
@Slf4j
public class AsyncController {

    @Autowired
    private MockQueue mockQueue;

    @Autowired
    private DeferredResultHolder deferredResultHolder;


    @RequestMapping(value = "order")
    public DeferredResult<String> order() throws Exception {

        log.info("主线程开始");

        // 随机订单号8位
        String orderNumber = RandomStringUtils.randomNumeric(8);
        mockQueue.setPlaceOrder(orderNumber);

        DeferredResult<String> result = new DeferredResult<>();
        deferredResultHolder.getMap().put(orderNumber, result);

//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				logger.info("副线程开始");
//				Thread.sleep(1000);
//				logger.info("副线程返回");
//				return "success";
//			}
//		};
        log.info("主线程返回");
        return result;
    }

}
