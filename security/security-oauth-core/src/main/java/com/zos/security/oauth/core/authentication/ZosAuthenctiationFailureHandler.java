package com.zos.security.oauth.core.authentication;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zos.security.core.properties.SecurityConstants;
import com.zos.security.core.support.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Oauth2 环境下认证失败处理器
 * 
 * @author 01Studio
 *
 */
@Slf4j
@Component("zosAuthenctiationFailureHandler")
public class ZosAuthenctiationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.AuthenticationFailureHandler#onAuthenticationFailure(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.AuthenticationException)
	 *
	 */
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("登录失败");
		
		if (exception instanceof BadCredentialsException) {
			// 认证失败强制跳转到注册服务接口
			response.sendRedirect(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL); 
			return;
		}
		
		response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(objectMapper.writeValueAsString(new BaseResponse(exception.getMessage())));
		
	}

}
