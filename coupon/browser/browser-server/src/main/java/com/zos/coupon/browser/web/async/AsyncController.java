package com.zos.coupon.browser.web.async;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @author 01Studio
 *
 */
@Slf4j
@RestController
public class AsyncController {
	
	@Autowired
	private MockQueue mockQueue;
	
	@Autowired
	private DeferredResultHolder deferredResultHolder;
	
	@RequestMapping("/order")
	public DeferredResult<String> order() throws Exception {
		log.info("主线程开始");
		
		String orderNumber = RandomStringUtils.randomNumeric(8);
		mockQueue.setPlaceOrder(orderNumber);
		
		DeferredResult<String> result = new DeferredResult<>();
		deferredResultHolder.getMap().put(orderNumber, result);
		
		return result;
		
//		Callable<String> result = new Callable<String>() {
//			@Override
//			public String call() throws Exception {
//				log.info("副线程开始");
//				Thread.sleep(1000);
//				log.info("副线程返回");
//				return "success";
//			}
//		};
	}

}
