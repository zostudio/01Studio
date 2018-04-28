package com.zos.security.oauth.core.security;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetails;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.mapper.UserMapper;
import com.zos.security.rbac.service.UserService;

/**
 * @author 01Studio
 *
 */
@Slf4j
@Component
@Transactional
public class OauthUserDetailsService implements UserDetailsService, SocialUserDetailsService {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	UserService userService;
	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		log.info("表单登录用户名:" + username);
//		Admin admin = adminRepository.findByUsername(username);
//		admin.getUrls();
//		return admin;
		return buildUser(username);
	}

	@Override
	public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException {
		log.info("社交登录用户Id: " + userId);
		return buildUser(userId);
	}

	private SocialUserDetails buildUser(String userId) {
		// 根据用户名查找用户信息
		//根据查找到的用户信息判断用户是否被冻结
		String password = passwordEncoder.encode("123456");
		log.info("数据库密码是: "+password);
		
		MySocialUserInfoBak socialUser = new MySocialUserInfoBak(userId, password,
				true, true, true, true,
				AuthorityUtils.commaSeparatedStringToAuthorityList("xxx,ROLE_USER"));
		socialUser.setAgentCode("my007");
		
		
		
		User user = UserMapper.INSTANCE.boToDomain(userService.findByUsername(userId));
		
		return user;
	}

}
