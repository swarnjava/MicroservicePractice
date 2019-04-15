package com.swarn.client;

import java.util.List;

import org.json.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class ClientController {

	
	@Autowired
	private MyFeignClient pc;
	
	@HystrixCommand(fallbackMethod = "fallback1")
	@RequestMapping("/test")
	public String testFeign()
	{
		String respmssg=pc.getMessageData();
		
		System.out.println("from ZuulProxy-Eureka: "+respmssg);
		return respmssg;
		
	}
	
	
	@HystrixCommand(fallbackMethod = "fallback2")
	@RequestMapping("/getStudentDetail")
	public String getStudentDetail()
	{
		String respmssg=pc.getStudentInfo();
		
		System.out.println("from ZuulProxy-Eureka: "+respmssg);
		return respmssg;
		
	}
	
	
	
	
	
	public String fallback1(Throwable hystrixCommand)
	{
		return "Server End is down.Please Try after some time";
	}
	
	public String fallback2(Throwable hystrixCommand)
	{
		return "Student Service is Temporary Down";
	}
}
