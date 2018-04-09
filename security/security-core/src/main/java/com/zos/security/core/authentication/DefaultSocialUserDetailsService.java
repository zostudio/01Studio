/**
 * 
 */
package com.zos.security.core.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;

/**
 * 默认第三方用户信息认证器, 默认的 SocialUserDetailsService 实现,
 * 不做任何处理, 只在控制台打印一句日志, 然后抛出异常, 提醒业务系统自己配置 SocialUserDetailsService
 * 
 * @author 01Studio
 *
 */
@Slf4j
public class DefaultSocialUserDetailsService implements SocialUserDetailsService {

	/**
	 * 查询第三方用户信息
	 *
	 * @param userId 第三方用户在第三方认证服务中的主键
	 * @return 第三方用户信息
	 * @throws UsernameNotFoundException 因未实现业务逻辑, 因此抛出异常
	 */
	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.warn("请配置 SocialUserDetailsService 接口的实现");
		throw new UsernameNotFoundException(userId);
	}

}
