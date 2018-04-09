package com.zos.coupon.app.resource.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author 01Studio
 *
 */
@Data
@Entity
public class Employee {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(length = 12)
	private String name;

}
