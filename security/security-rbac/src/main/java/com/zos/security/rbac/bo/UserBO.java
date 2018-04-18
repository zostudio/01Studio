package com.zos.security.rbac.bo;

import lombok.Data;

@Data
public class UserBO {
	
	private Long id;
	
    private String name;
    
    private int age;
    
    private String address;
    
    private String pwd;
}
