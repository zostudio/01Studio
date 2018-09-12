package com.zos.security.rbac.web.controller.impl;

import com.zos.security.rbac.bo.UserBO;
import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.UserRoleDTO;
import com.zos.security.rbac.dto.param.UserParamDTO;
import com.zos.security.rbac.dto.param.UserRoleRelationDTO;
import com.zos.security.rbac.mapper.UserMapper;
import com.zos.security.rbac.mapper.UserRoleMapper;
import com.zos.security.rbac.repository.support.QueryResultConverter;
import com.zos.security.rbac.service.RbacService;
import com.zos.security.rbac.service.UserService;
import com.zos.security.rbac.web.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
public class UserControllerImpl implements UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	RbacService rbacService;

	@Override
	public UserDTO create(@RequestBody UserDTO userDTO) {
		return UserMapper.INSTANCE.boToDTO(userService.create(UserMapper.INSTANCE.dtoToBO(userDTO)));
	}

	@Override
	public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO) {
		return UserMapper.INSTANCE.boToDTO(userService.update(id, UserMapper.INSTANCE.dtoToBO(userDTO)));
	}

	@Override
	public void delete(@PathVariable Long id) {
		userService.delete(id);
	}

	@Override
	public UserDTO getInfo(@PathVariable Long id) {
		return UserMapper.INSTANCE.boToDTO(userService.getInfo(id));
	}

	@Override
	public Page<UserDTO> query(UserParamDTO userConditionDTO, @PageableDefault(direction = Direction.DESC, page = 0, size = 10, sort = {"id"}) Pageable pageable) {
		Page<UserBO> pageUserBO = userService.query(UserMapper.INSTANCE.dtoToBO(userConditionDTO), pageable);
		Page<UserDTO> pageUserDTO = QueryResultConverter.convert(UserMapper.INSTANCE.boToDTO(pageUserBO.getContent()), pageable, pageUserBO.getTotalElements());
		return pageUserDTO;
	}

	@Override
	public List<UserRoleDTO> addRoles(@RequestBody UserRoleRelationDTO userRoleRelationDTO) {
		return UserRoleMapper.INSTANCE.boToDTO(userService.addRoles(UserRoleMapper.INSTANCE.dtoToBO(userRoleRelationDTO)));
	}

	@Override
	public void delRoles(@PathVariable Long id, @RequestBody UserRoleRelationDTO userRoleRelationDTO) {
		userService.delRoles(id, UserRoleMapper.INSTANCE.dtoToBO(userRoleRelationDTO));
	}

	@Override
	public Long updatePwd(@PathVariable Long id, @RequestBody UserParamDTO userConditionDTO) {
		return userService.updatePwd(id, UserMapper.INSTANCE.dtoToBO(userConditionDTO));
	}

	@Override
	public Set<User.RoleCache> getRoles(@PathVariable Long id, Authentication authentication) {
		if ("Administrator".equals(authentication.getName())) {
			return null;
		} else {
			if (!rbacService.hasUserRoleCache(String.valueOf(id))) {
				rbacService.refreshCachePermission(null, authentication.getPrincipal());
			}
			if (rbacService.hasUserRoleCache(String.valueOf(id))) {
				return rbacService.getUserRoleCaches(String.valueOf(id));
			}
		}
		return null;
	}

	@Override
	public Authentication getAuthentication(Authentication authentication) {
		return authentication;
	}

	@Override
	public boolean existsUserByUsername(String username) {
		return userService.findByUsername(username) != null ? true : false;
	}

	@Override
	public boolean existsUserByEmail(String email) {
		return userService.findByEmail(email) != null ? true : false;
	}

	@Override
	public boolean existsUserByPhone(String phone) {
		return userService.findByPhone(phone) != null ? true : false;
	}
	
}
