package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/restart")
public class RestartTestController {
	@RequestMapping("/test")
    @ResponseBody
    public String test() {
		System.err.println("测试成功");
		System.err.println("测试失败");
		
		return "";
	}
}
