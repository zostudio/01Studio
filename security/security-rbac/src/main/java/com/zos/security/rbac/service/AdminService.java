package com.zos.security.rbac.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zos.security.rbac.dto.AdminCondition;
import com.zos.security.rbac.dto.AdminInfo;

/**
 * 管理员服务
 * 
 * @author 01Studio
 *
 */
public interface AdminService {

	/**
	 * 创建管理员
	 *
	 * @param adminInfo 管理员信息
	 * @return 管理员信息
	 */
	AdminInfo create(AdminInfo adminInfo);

	/**
	 * 修改管理员
	 *
	 * @param adminInfo 管理员信息
	 * @return 管理员信息
	 */
	AdminInfo update(AdminInfo adminInfo);

	/**
	 * 删除管理员
	 *
	 * @param id 管理员主键
	 */
	void delete(Long id);

	/**
	 * 获取管理员详细信息
	 *
	 * @param id 管理员主键
	 * @return 管理员详细信息
	 */
	AdminInfo getInfo(Long id);

	/**
	 * 分页查询管理员
	 *
	 * @param condition 查询条件
	 * @param pageable 分页参数
	 * @return 分页管理员数据
	 */
	Page<AdminInfo> query(AdminCondition condition, Pageable pageable);

}
