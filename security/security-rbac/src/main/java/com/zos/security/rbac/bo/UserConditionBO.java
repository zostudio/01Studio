package com.zos.security.rbac.bo;

import lombok.Data;

@Data
public class UserConditionBO {
	
	private Long id;
	
    private String username;
    
    private String oldPassword;
    
    private String newPassword;
}
