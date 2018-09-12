package com.example.demo.dao.cluster;

import org.apache.ibatis.annotations.Param;

import com.example.demo.domain.entity.cluster.User;


public interface UserMapper {
	
	/**
	 * 判断用户名密码是否正确
	 * @param userId
	 * @return
	 */
	 public User selectByUserId(@Param(value="userId") String userId,@Param(value="password") String password); 
}
