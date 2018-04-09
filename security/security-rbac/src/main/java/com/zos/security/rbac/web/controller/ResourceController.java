package com.zos.security.rbac.web.controller;

import com.zos.security.core.support.SimpleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zos.security.rbac.domain.Admin;
import com.zos.security.rbac.dto.ResourceInfo;
import com.zos.security.rbac.service.ResourceService;

/**
 * @author 01Studio
 *
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {
	
	@Autowired
	private ResourceService resourceService;
	
	/**
	 * 获取资源树
	 *
	 * @param admin 管理员信息
	 * @return 资源信息
	 */
	@GetMapping
	public ResourceInfo getTree(@AuthenticationPrincipal Admin admin){
		return resourceService.getTree(admin.getId());
	}

	/**
	 * 获取资源信息
	 *
	 * @param id 资源主键
	 * @return 资源信息
	 */
	@GetMapping("/{id}")
	public ResourceInfo getInfo(@PathVariable Long id){
		return resourceService.getInfo(id);
	}
	/**
	 * 创建资源
	 *
	 * @param info 资源信息
	 * @return 资源信息
	 */
	@PostMapping
	public ResourceInfo create(@RequestBody ResourceInfo info){
		if(info.getParentId() == null) {
			info.setParentId(0L);
		}
		return resourceService.create(info);
	}
	/**
	 * 修改资源
	 *
	 * @param info 资源信息
	 * @return 资源信息
	 */
	@PutMapping("/{id}")
	public ResourceInfo update(@RequestBody ResourceInfo info){
		return resourceService.update(info);
	}
	/**
	 * 删除
	 *
	 * @param id 资源主键
	 */
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		resourceService.delete(id);
	}
	
	/**
	 * 资源上移
	 *
	 * @param id 资源主键
	 * @return 响应体
	 */
	@PostMapping("/{id}/up")
	public SimpleResponse moveUp(@PathVariable Long id){
		return new SimpleResponse(resourceService.move(id, true));
	}
	/**
	 * 资源下移
	 *
	 * @param id 资源主键
	 * @return 响应体
	 */
	@PostMapping("/{id}/down")
	public SimpleResponse moveDown(@PathVariable Long id){
		return new SimpleResponse(resourceService.move(id, false));
	}

}
