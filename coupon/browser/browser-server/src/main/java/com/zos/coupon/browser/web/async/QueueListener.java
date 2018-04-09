package com.zos.coupon.browser.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * @author 01Studio
 *
 */
@Slf4j
@Component
public class QueueListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private MockQueue mockQueue;

	@Autowired
	private DeferredResultHolder deferredResultHolder;

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		new Thread(() -> {
			while (true) {

				if (StringUtils.isNotBlank(mockQueue.getCompleteOrder())) {
					
					String orderNumber = mockQueue.getCompleteOrder();
					log.info("返回订单处理结果: "+orderNumber);
					deferredResultHolder.getMap().get(orderNumber).setResult("place order success");
					mockQueue.setCompleteOrder(null);
					
				}else{
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}
}
