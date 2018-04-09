package com.zos.security.rbac.authentication;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.zos.security.rbac.domain.Admin;
import com.zos.security.rbac.repository.AdminRepository;

/**
 * @author 01Studio
 *
 */
@Slf4j
@Transactional
@Component("userDetailsService")
public class RbacUserDetailsService implements UserDetailsService {
	
	@Autowired
	private AdminRepository adminRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("表单登录用户名: " + username);
		Admin admin = adminRepository.findByUsername(username);
		//admin.getUrls();
		return admin;
	}

}
