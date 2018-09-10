package com.zos.security.rbac.dto.condition;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserConditionDTO {
	
	private Long id;
	
    private String username;
    
    @JsonProperty(value = "old_password")
    private String oldPassword;

    @JsonProperty(value = "new_password")
    private String newPassword;
}
