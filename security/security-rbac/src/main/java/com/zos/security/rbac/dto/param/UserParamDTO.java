package com.zos.security.rbac.dto.param;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class UserParamDTO {
	
	private String id;
	
    private String username;
    
    @JsonProperty(value = "old_password")
    private String oldPassword;

    @JsonProperty(value = "new_password")
    private String newPassword;
}
