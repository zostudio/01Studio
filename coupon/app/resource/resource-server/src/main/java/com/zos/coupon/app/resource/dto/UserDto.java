package com.zos.coupon.app.resource.dto;

import lombok.Data;

@Data
public class UserDto {
	
	private Long id;
	
    private String name;
    
    private int age;
    
    private String address;
    
    private String pwd;
}
