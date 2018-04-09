package com.zos.security.rbac.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zos.security.rbac.dto.RoleInfo;
import com.zos.security.rbac.service.RoleService;

/**
 * @author 01Studio
 *
 */
@RestController
@RequestMapping("/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	/**
	 * 创建角色
	 *
	 * @param roleInfo 角色信息
	 * @return 角色信息
	 */
	@PostMapping
	public RoleInfo create(@RequestBody RoleInfo roleInfo) {
		return roleService.create(roleInfo);
	}
	
	/**
	 * 修改角色信息
	 *
	 * @param roleInfo 角色信息
	 * @return 角色信息
	 */
	@PutMapping("/{id}")
	public RoleInfo update(@RequestBody RoleInfo roleInfo) {
		return roleService.update(roleInfo);
	}
	
	/**
	 * 删除角色
	 *
	 * @param id 角色主键
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		roleService.delete(id);
	}

	/**
	 * 获取角色详情
	 *
	 * @param id 角色主键
	 * @return 角色信息
	 */
	@GetMapping("/{id}")
	public RoleInfo getInfo(@PathVariable Long id) {
		return roleService.getInfo(id);
	}

	/**
	 * 获取所有角色
	 *
	 * @return 角色信息列表
	 */
	@GetMapping
	public List<RoleInfo> findAll() {
		return roleService.findAll();
	}
	
	/**
	 * 获取角色的所有资源
	 *
	 * @param id 角色主键
	 * @return 资源列表
	 */
	@GetMapping("/{id}/resource")
	public String[] getRoleResources(@PathVariable Long id){
		return roleService.getRoleResources(id);
	}
	
	/**
	 * 创建用户的资源
	 *
	 * @param id 用户主键
	 * @param ids 资源列表
	 */
	@PostMapping("/{id}/resource")
	public void createRoleResource(@PathVariable Long id, String ids){
		roleService.setRoleResources(id, ids);
	}

}
