package com.zos.coupon.browser.service.impl;

import org.springframework.stereotype.Service;

import com.zos.coupon.browser.service.HelloService;

/**
 * @author 01Studio
 *
 */
@Service
public class HelloServiceImpl implements HelloService {

	/* (non-Javadoc)
	 * @see com.zos.coupon.browser.service.HelloService#greeting(java.lang.String)
	 */
	@Override
	public String greeting(String name) {
		System.out.println("greeting");
		return "hello "+name;
	}

}
