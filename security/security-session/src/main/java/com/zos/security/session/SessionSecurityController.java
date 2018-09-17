package com.zos.security.session;

import com.zos.security.core.properties.SecurityConstants;
import com.zos.security.core.properties.SecurityProperties;
import com.zos.security.core.social.SocialController;
import com.zos.security.core.social.support.SocialUserInfo;
import com.zos.security.core.support.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.ProviderSignInUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Session 环境下与安全相关的服务
 * 
 * @author 01Studio
 *
 */
@Slf4j
@RestController
public class SessionSecurityController extends SocialController {

	private RequestCache requestCache = new HttpSessionRequestCache();

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private ProviderSignInUtils providerSignInUtils;

	/**
	 * 当需要身份认证时, 跳转到这里
	 * 
	 * @param request 请求
	 * @param response 响应
	 * @return 响应数据
	 * @throws IOException 异常
	 */
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	@RequestMapping(SecurityConstants.DEFAULT_UNAUTHENTICATION_URL)
	public BaseResponse requireAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws IOException {

		SavedRequest savedRequest = requestCache.getRequest(request, response);

		if (savedRequest != null) {
			String targetUrl = savedRequest.getRedirectUrl();
			log.info("引发跳转的请求是: " + targetUrl);
			if (StringUtils.endsWithIgnoreCase(targetUrl, ".html")) {
				redirectStrategy.sendRedirect(request, response, securityProperties.getBrowser().getSignInPage());
			}
		}

		return new BaseResponse("访问的服务需要身份认证, 请引导用户到登录页");
	}

	/**
	 * 用户第一次社交登录时, 会引导用户进行用户注册或绑定, 此服务用于在注册或绑定页面获取社交网站用户信息
	 * 
	 * @param request 请求
	 * @return 社交帐号信息
	 */
	@GetMapping(SecurityConstants.DEFAULT_SOCIAL_USER_INFO_URL)
	public SocialUserInfo getSocialUserInfo(HttpServletRequest request) {
		Connection<?> connection = providerSignInUtils.getConnectionFromSession(new ServletWebRequest(request));
		return buildSocialUserInfo(connection);
	}

}
