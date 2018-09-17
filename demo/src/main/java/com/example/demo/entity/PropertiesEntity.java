package com.example.demo.entity;

import org.springframework.web.bind.annotation.ModelAttribute;


public class PropertiesEntity {
	private String name;
	@ModelAttribute
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
