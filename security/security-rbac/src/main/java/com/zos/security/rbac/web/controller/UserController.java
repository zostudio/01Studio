package com.zos.security.rbac.web.controller;

import com.zos.security.rbac.domain.User;
import com.zos.security.rbac.dto.condition.UserConditionDTO;
import com.zos.security.rbac.dto.UserDTO;
import com.zos.security.rbac.dto.UserRoleDTO;
import com.zos.security.rbac.dto.condition.UserRoleRelationDTO;
import io.swagger.annotations.Api;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/user")
@Api(value="用户接口")
public interface UserController {

	@PostMapping
	public UserDTO create(@RequestBody UserDTO userDTO);
	
	@PutMapping("/{id:\\d+}")
	public UserDTO update(@PathVariable Long id, @RequestBody UserDTO userDTO);
	
	@DeleteMapping("/{id:\\d+}")
	public void delete(@PathVariable Long id);
	
	@GetMapping("/{id:\\d+}")
	public UserDTO getInfo(@PathVariable Long id);
	
	@GetMapping
	public Page<UserDTO> query(UserConditionDTO userConditionDTO, Pageable pageable);
	
	@PutMapping("/pwd/{id:\\d+}")
	public Long updatePwd(@PathVariable Long id, @RequestBody UserConditionDTO userConditionDTO);
	
	@PostMapping("/roles")
	public List<UserRoleDTO> addRoles(@RequestBody UserRoleRelationDTO userRoleRelationDTO);
	
	@GetMapping("/roles/{id:\\d+}")
	public Set<User.RoleCache> getRoles(@PathVariable Long id, Authentication authentication);
	
	@DeleteMapping("/roles/{id:\\d+}")
	public void delRoles(@PathVariable Long id, @RequestBody UserRoleRelationDTO userRoleRelationDTO);
	
	@GetMapping("/authentication")
	public Authentication getAuthentication(Authentication authentication);
	
	@GetMapping("/exists/username/{username}")
	public boolean existsUserByUsername(@PathVariable String username);
	
	@GetMapping("/exists/email/{email}")
	public boolean existsUserByEmail(@PathVariable String email);
	
	@GetMapping("/exists/phone/{phone}")
	public boolean existsUserByPhone(@PathVariable String phone);
}
