package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/value")
public class ValueTestCntroller {
	@Value("${name}")
	String name;
	@RequestMapping("/test")
	public String test() {
		return name;
	}

}
