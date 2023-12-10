package com.example.demo.controller;

import com.example.demo.domain.LoginInfo;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.io.IOException;

@RestController
@RequestMapping("/jiao")
public class JiAoTestController {

	@Resource(name = "1,", lookup = "xxx")
	LoginInfo loginInfo;

	@RequestMapping("/test")
	@ResponseBody
	public String getWeChatUserInfo(String token, String openid) {
		System.err.println("更改生效了");
		//System.err.println("更改文档内容");  
		String urlNameString = "http://10.15.102.232:9080/internalcall/source/jiao/test";

		String result = "";
		String name = "";

		// 根据地址获取请求
		HttpGet request = new HttpGet(urlNameString);//这里发送get请求
		// 获取当前客户端对象
		HttpClient httpClient = new DefaultHttpClient();
		// 通过请求对象获取响应对象
		try {
			HttpResponse response = httpClient.execute(request);
			result = EntityUtils.toString(response.getEntity(), "utf-8");
		} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
            
        return result;
    }
}
