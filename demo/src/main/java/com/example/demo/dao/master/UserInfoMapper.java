package com.example.demo.dao.master;

import com.example.demo.domain.entity.master.UserInfo;

public interface UserInfoMapper {

    UserInfo selectByUserId(String userId);
}