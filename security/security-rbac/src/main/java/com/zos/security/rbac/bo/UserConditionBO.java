package com.zos.security.rbac.bo;

import lombok.Data;

@Data
public class UserConditionBO {
	
	private String id;
	
    private String username;
    
    private String oldPassword;
    
    private String newPassword;
}
