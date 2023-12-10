package com.example.demo.mock;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OneLifeAPIServiceImpl implements OneLifeAPIService {

    @Autowired
    public CommonOelifeAPIService commonOelifeAPIService;

    public PolicyRegisterOrValidDTO requestAPI(String applicationWSJsonStr, String eposSalesNo, String applNo, String channel, String paperNo, String type) {
        //API接口调用
        ApiApplicationDTO apiApplicationDto = JSONObject.parseObject(applicationWSJsonStr, ApiApplicationDTO.class);
        apiApplicationDto.setEpossalesno(eposSalesNo);
        apiApplicationDto.setPaperNo(paperNo.substring(0, 10));
        if (StringUtils.isNotBlank(applNo) && StringUtils.isNotBlank(type)) {
            apiApplicationDto.setChannel(channel);
            return commonOelifeAPIService.ePolicyRegister(apiApplicationDto);
        }
        return commonOelifeAPIService.ePolicyValid(apiApplicationDto);
    }

}
