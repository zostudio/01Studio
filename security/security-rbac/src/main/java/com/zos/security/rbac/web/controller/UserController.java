package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.param.UserParamDTO;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/user")
@Api(value="用户接口")
public interface UserController {

	@PostMapping
	public UserDTO create(@RequestBody UserDTO userDTO);
	
	@PutMapping("/{id:\\d+}")
	public UserDTO update(@PathVariable String id, @RequestBody UserDTO userDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable String id);
	
	@GetMapping("/{id:\\d+}")
	public UserDTO getInfo(@PathVariable String id);
	
	@GetMapping
	public Page<UserDTO> query(UserParamDTO userConditionDTO, Pageable pageable);
	
	@PutMapping("/pwd/{id:\\d+}")
	public Long updatePwd(@PathVariable String id, @RequestBody UserParamDTO userConditionDTO);

	//TODO 添加角色
//	@PostMapping("/roles")
//	public List<UserRoleDTO> addRoles(@RequestBody UserRoleRelationDTO userRoleRelationDTO);
//
	@GetMapping("/roles/{id:\\d+}")
	public Set<User.RoleCache> getRoles(@PathVariable String id, Authentication authentication);
	
//	@DeleteMapping("/roles/{id:\\d+}")
//	public void delRoles(@PathVariable String id, @RequestBody UserRoleRelationDTO userRoleRelationDTO);
//
	@GetMapping("/authentication")
	public Authentication getAuthentication(Authentication authentication);
	
	@GetMapping("/exists/username/{username}")
	public boolean existsUserByUsername(@PathVariable String username);
	
	@GetMapping("/exists/email/{email}")
	public boolean existsUserByEmail(@PathVariable String email);
	
	@GetMapping("/exists/phone/{phone}")
	public boolean existsUserByPhone(@PathVariable String phone);
}
