package com.zos.coupon.browser.web.config;

import java.util.ArrayList;
import java.util.List;

import com.zos.coupon.browser.security.DemoUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.zos.coupon.browser.web.filter.TimeFilter;
import com.zos.coupon.browser.web.interceptor.TimeInterceptor;

/**
 * @author 01Studio
 *
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@SuppressWarnings("unused")
	@Autowired
	private TimeInterceptor timeInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
//		registry.addInterceptor(timeInterceptor);
	}
	
//	@Bean
	public FilterRegistrationBean timeFilter() {
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		
		return registrationBean;
		
	}

	@Bean
	@ConditionalOnMissingBean(name = "userDetailsService")
	public UserDetailsService userDetailsService() {
		return new DemoUserDetailsService();
	}

}
