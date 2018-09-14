package com.zos.security.core.authentication;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * 默认用户信息认证器, 默认的 UserDetailsService 实现,
 * 不做任何处理, 只在控制台打印一句日志, 然后抛出异常, 提醒业务系统配置自定义的 UserDetailsService 实现
 * 
 * @author 01Studio
 *
 */
@Slf4j
public class DefaultUserDetailsService implements UserDetailsService {

	/**
	 * 查询用户信息
	 *
	 * @param username 账号
	 * @return 用户信息
	 * @throws UsernameNotFoundException 因未实现业务逻辑, 因此抛出异常
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.warn("请配置 UserDetailsService 接口的实现");
		throw new UsernameNotFoundException(username);
	}

}
