/**
 *
 */
package cn.zyblogs.web.async;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @Title: MockQueue.java
 * @Package cn.zyblogs.web.async
 * @Description: TODO  模拟队列
 * @Author ZhangYB
 * @Version V1.0
 */
@Component
@Slf4j
@Getter
@Setter
public class MockQueue {

    /**
     * 下单消息
     */
    private String placeOrder;

    /**
     * 完成订单消息
     */
    private String completeOrder;


    public void setPlaceOrder(String placeOrder) throws Exception {
        new Thread(() -> {
            log.info("接到下单请求, " + placeOrder);
            try {
                Thread.sleep(1_000);
            } catch (Exception e) {
                e.printStackTrace();
            }
            // 完成订单消息
            this.completeOrder = placeOrder;
            log.info("下单请求处理完毕," + placeOrder);
        }).start();
    }


}
