package com.client2.controller;

import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

@RestController
public class Client2Controller {

	RestTemplate restTemplate;
    
    public Client2Controller(){
        restTemplate = new RestTemplate();
    }
	
	
	@RequestMapping("/getStudent")
	public String showStudent()throws ParseException
	{
		String data="";
		 JSONParser parser = new JSONParser();
         ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

		
		String apiUrl = "http://localhost:8082/hello/getStudent_client1";//as eureka-service is running on 8768 port
        try
        {
        	HttpHeaders headers = new HttpHeaders();
        	 HttpEntity<String> request = new HttpEntity<String>(headers);
	            ResponseEntity<String> response = restTemplate.exchange(apiUrl, HttpMethod.GET, request, String.class);
	            Object jsonobj = response.getBody();
	            
	           	//String stringToParse = ow.writeValueAsString(jsonobj);
	            JSONObject jsonobject = (JSONObject) parser.parse(jsonobj.toString());
	            System.out.println(jsonobject.get("sid")+"---"+jsonobject.get("sname")+"---"+jsonobject.get("saddr"));
	            data=jsonobject.get("sid")+"---"+jsonobject.get("sname")+"---"+jsonobject.get("saddr");
	            
        }
        catch(Exception q)
        {
        	q.printStackTrace();
        }
		
		return data;
	}
}
