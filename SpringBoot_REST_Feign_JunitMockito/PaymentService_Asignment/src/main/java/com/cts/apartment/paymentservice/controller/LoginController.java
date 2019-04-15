package com.cts.apartment.paymentservice.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.apartment.paymentservice.model.UserInfo;
import com.cts.apartment.paymentservice.service.LoginServiceIntrfc;
import com.google.gson.Gson;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/loginservice")
public class LoginController {
	
	
	@Autowired
	private LoginServiceIntrfc loginservice;
	

	@CrossOrigin
	@RequestMapping(value="/checkLogin",method=RequestMethod.POST,consumes = "application/json",produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> checkLogin(@RequestBody Map<String, Object> payload)
	{
		Map mp=new HashMap();
		
		if(payload!=null && payload.isEmpty()==false)
		{
			String username=(String)payload.get("username");
			String pwd=(String)payload.get("password");
			UserInfo u=loginservice.findUserByUserNameandPassword(username, pwd);
			if(u==null||u.getUiid()==null||u.getUiid()<1)
			{
				mp.put("status", "FALSE");
			}
			else
			{
				mp.put("status", "TRUE");
				mp.put("role", u.getUrole());
			}
		}
		else
		{
			mp.put("status", "IGNORED");
		}
		
		Gson gson=new Gson();
		String respstr=gson.toJson(mp);
		
		return new ResponseEntity<String>(respstr,HttpStatus.OK);
	}
}
