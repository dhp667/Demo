package com.example.demo.dao.cluster;

import com.example.demo.domain.entity.cluster.User;
import org.apache.ibatis.annotations.Param;


public interface UserMapper {

    /**
     *
     */
    public User selectByUserId(@Param(value = "userId") String userId, @Param(value = "password") String password);
}
