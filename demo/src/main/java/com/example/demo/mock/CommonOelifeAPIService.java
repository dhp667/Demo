package com.example.demo.mock;

import org.springframework.stereotype.Component;

@Component
public interface CommonOelifeAPIService {

    /***
     * @title ePolicyValid
     * @Author: E2209
     * @Description: 保单校验
     * @Date: 2023/4/17 15:04
     * @Param param:
     * @return: java.lang.PolicyRegisterOrValidVO
     **/
    PolicyRegisterOrValidDTO ePolicyValid(ApiApplicationDTO param);


    /***
     * @title ePolicyRegister
     * @Author: E2209
     * @Description: 保单注册
     * @Date: 2023/4/17 15:04
     * @Param param:
     * @return: java.lang.PolicyRegisterOrValidVO
     **/
    PolicyRegisterOrValidDTO ePolicyRegister(ApiApplicationDTO param);
}
