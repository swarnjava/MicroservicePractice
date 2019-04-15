package com.swarn.controller;

import java.util.Date;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.swarn.constant.AppConstant;
import com.swarn.model.Student;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
public class MyController {

	@RequestMapping("/getMessage")
	  public String helloWorld(HttpServletRequest request,@RequestHeader HttpHeaders headers) {
		System.out.println("Header Data: "+""+headers.get("Authorization"));
		
		String jwtToken = Jwts.builder().
				 setSubject("LoginAuthentication").
				 claim("roles", "user").
				 claim("username", "swarn").
				 claim("scope", "self groups/admins").
				 claim("code", "S1001").
				 setIssuedAt(new Date()).
				 setId("10001")
		         .signWith(SignatureAlgorithm.HS256,AppConstant.secretkey).compact();
		
		return "Hello Swarn:  "+jwtToken;
	  }
	
	
	@RequestMapping(value="/getStudent_client1",produces=MediaType.APPLICATION_JSON_VALUE)
	public Student getStudent()
	{
		Student studentobj=new Student(101,"Ram","Kolkata");
		
		return studentobj;
	}
	
	
	
}
