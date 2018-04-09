package com.zos.security.rbac.init;

import java.util.HashSet;
import java.util.Set;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.zos.security.rbac.domain.Admin;
import com.zos.security.rbac.domain.Resource;
import com.zos.security.rbac.domain.ResourceType;
import com.zos.security.rbac.domain.Role;
import com.zos.security.rbac.domain.RoleAdmin;
import com.zos.security.rbac.repository.AdminRepository;
import com.zos.security.rbac.repository.ResourceRepository;
import com.zos.security.rbac.repository.RoleAdminRepository;
import com.zos.security.rbac.repository.RoleRepository;

/**
 * 默认的系统数据初始化器, 永远在其他数据初始化器之前执行
 * 
 * @author 01Studio
 *
 */
@Slf4j
@Component
public class AdminDataInitializer extends AbstractDataInitializer {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private AdminRepository adminRepository;

	@Autowired
	private RoleAdminRepository roleAdminRepository;

	@Autowired
	protected ResourceRepository resourceRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idea.core.spi.initializer.DataInitializer#getIndex()
	 */
	@Override
	public Integer getIndex() {
		return Integer.MIN_VALUE;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idea.core.spi.initializer.AbstractDataInitializer#doInit()
	 */
	@Override
	protected void doInit() {
		initResource();
		Role role = initRole();
		initAdmin(role);
	}

	/**
	 * 初始化用户数据
	 *
	 * @param role 角色
	 */
	private void initAdmin(Role role) {
		// 管理员帐号
		Admin admin = new Admin();
		admin.setUsername("admin");
		admin.setPassword(passwordEncoder.encode("123456"));
		adminRepository.save(admin);

		RoleAdmin roleAdmin = new RoleAdmin();
		roleAdmin.setRole(role);
		roleAdmin.setAdmin(admin);
		roleAdminRepository.save(roleAdmin);
	}

	/**
	 * 初始化角色数据
	 *
	 * @return 角色
	 */
	private Role initRole() {
		Role role = new Role();
		role.setName("超级管理员");
		roleRepository.save(role);
		return role;
	}

	/**
	 * 初始化菜单数据
	 */
	protected void initResource() {
		Resource root = createRoot("根节点");

		createResource("首页", "", "home", root);

		Resource menu1 = createResource("平台管理", "", "desktop", root);

		createResource("资源管理", "resource", "", menu1);
		createResource("角色管理", "role", "", menu1);
		createResource("管理员管理", "admin", "", menu1);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.idea.core.spi.initializer.AbstractDataInitializer#isNeedInit()
	 */
	@Override
	protected boolean isNeedInit() {
		log.info(adminRepository.count() + "");
		System.out.println(adminRepository.count());
		return adminRepository.count() == 0;
	}

	/**
	 * 创建根节点
	 *
	 * @param name 根节点
	 * @return 根节点
	 */
	protected Resource createRoot(String name) {
		Resource node = new Resource();
		node.setName(name);
		resourceRepository.save(node);
		return node;
	}

	/**
	 * 创建资源
	 *
	 * @param name 名称
	 * @param parent 父节点
	 * @return 资源
	 */
	protected Resource createResource(String name, Resource parent) {
		return createResource(name, null, null, parent);
	}

	/**
	 * 创建资源
	 *
	 * @param name 名称
	 * @param link 链接
	 * @param iconName 图标
	 * @param parent 父节点
	 * @return 资源
	 */
	protected Resource createResource(String name, String link, String iconName, Resource parent) {
		Resource node = new Resource();
		node.setName(name);
		node.setIcon(iconName);
		node.setParent(parent);
		node.setType(ResourceType.MENU);
		if (StringUtils.isNotBlank(link)) {
			node.setLink(link + "Manage");
			Set<String> urls = new HashSet<>();
			urls.add(link + "Manage");
			urls.add("/" + link + "/**");
			node.setUrls(urls);
		}
		resourceRepository.save(node);
		return node;
	}
}
