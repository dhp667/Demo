package com.example.demo.dao.master;

import com.example.demo.domain.entity.master.UserInfo;

public interface UserInfoMapper {
	/**
	 * 根据用户编号获取用户
	 * @param userId
	 * @return
	 */
    UserInfo selectByUserId(String userId);
}