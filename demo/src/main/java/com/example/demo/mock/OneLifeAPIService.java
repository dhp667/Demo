package com.example.demo.mock;

public interface OneLifeAPIService {

    public PolicyRegisterOrValidDTO requestAPI(String applicationWSJsonStr, String eposSalesNo, String applNo,
                                               String channel, String paperNo, String type);
}
