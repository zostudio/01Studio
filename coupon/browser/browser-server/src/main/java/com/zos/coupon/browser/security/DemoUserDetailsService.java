package com.zos.coupon.browser.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUser;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author 01Studio
 *
 */
@Slf4j
@Transactional
public class DemoUserDetailsService implements UserDetailsService, SocialUserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		log.info("表单登录账号:" + username);
//		Admin admin = adminRepository.findByUsername(username);
//		admin.getUrls();
//		return admin;
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("设计登录用户Id: " + userId);
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		// 根据账号查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		log.info("数据库密码是: "+password);
		
		return new SocialUser(userId, password,
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("xxx"));
	}

}
