package com.example.demo.domain;

import lombok.Data;

@Data
public class LoginInfo {

	/**登录名*/
	private String loginName;
	
	/**手机号*/
	private String mobile;
	
	/**工号*/
	private String jobNum;
	
	/**登录密码*/
	private String loginPwd;
	
	/**验证码*/
	private String verCode;
	
	private String imeiId;
	
	/**检查设备绑定状态*/
	private String checkBindDeviceStatus;
}
