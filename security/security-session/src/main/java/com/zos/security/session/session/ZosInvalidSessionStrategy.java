package com.zos.security.session.session;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.session.InvalidSessionStrategy;

import com.zos.security.core.properties.SecurityProperties;

/**
 * 默认的 Session 失效处理策略
 * 
 * @author 01Studio
 *
 */
public class ZosInvalidSessionStrategy extends AbstractSessionStrategy implements InvalidSessionStrategy {

	public ZosInvalidSessionStrategy(SecurityProperties securityProperties) {
		super(securityProperties);
	}

	@Override
	public void onInvalidSessionDetected(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		onSessionInvalid(request, response);
	}

}
