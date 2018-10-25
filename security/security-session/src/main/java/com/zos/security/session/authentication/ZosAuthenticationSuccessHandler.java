package com.zos.security.session.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zos.security.core.properties.SignInResponseType;
import com.zos.security.core.properties.SecurityProperties;
import com.zos.security.core.support.BaseResponse;

/**
 * 浏览器环境下登录成功的处理器
 * 
 * @author 01Studio
 *
 */
@Slf4j
@Component("zosAuthenticationSuccessHandler")
public class ZosAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private SecurityProperties securityProperties;

	private RequestCache requestCache = new HttpSessionRequestCache();

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.web.authentication.
	 * AuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.
	 * HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {

		log.info("登录成功");

		if (SignInResponseType.JSON.equals(securityProperties.getBrowser().getSignInResponseType())) {
			response.setContentType("application/json;charset=UTF-8");
			String type = authentication.getClass().getSimpleName();
			response.getWriter().write(objectMapper.writeValueAsString(new BaseResponse(type)));
		} else {
			// 如果设置了zos.security.browser.singInSuccessUrl, 总是跳到设置的地址上
			// 如果没设置, 则尝试跳转到登录之前访问的地址上, 如果登录前访问地址为空, 则跳到网站根路径上
			if (StringUtils.isNotBlank(securityProperties.getBrowser().getSingInSuccessUrl())) {
				requestCache.removeRequest(request, response);
				setAlwaysUseDefaultTargetUrl(true);
				setDefaultTargetUrl(securityProperties.getBrowser().getSingInSuccessUrl());
			}
			super.onAuthenticationSuccess(request, response, authentication);
		}

	}

}
