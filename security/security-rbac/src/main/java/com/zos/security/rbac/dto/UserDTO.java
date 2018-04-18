package com.zos.security.rbac.dto;

import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	public interface SimpleView {};
	public interface DetailView extends SimpleView {};
	
	private Long id;
	
    private String name;
    
    private int age;
    
    private String address;
    
    private String pwd;

    @JsonView(SimpleView.class)
	public Long getId() {
		return id;
	}

    @JsonView(SimpleView.class)
	public String getName() {
		return name;
	}

    @JsonView(SimpleView.class)
	public int getAge() {
		return age;
	}

    @JsonView(DetailView.class)
	public String getAddress() {
		return address;
	}

    @JsonView(DetailView.class)
	public String getPwd() {
		return pwd;
	}
}
