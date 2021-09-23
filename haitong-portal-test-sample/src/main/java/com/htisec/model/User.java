package com.htisec.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
//@Entity
public class User {
	private int userId;
	private String name;

	public User() {
	}
 

}