package com.swarn.client;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;



@FeignClient(name="myservice",url="http://localhost:8082/")
public interface MyFeignClient {

	
	@RequestMapping(method = RequestMethod.GET, path = "hello/getMessage")
	public String getMessageData();
	
	
	@RequestMapping(method = RequestMethod.GET, path = "hello2/getStudent")
	public String getStudentInfo();
	
	
}
