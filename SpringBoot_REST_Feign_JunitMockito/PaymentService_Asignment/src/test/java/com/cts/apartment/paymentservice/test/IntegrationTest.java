package com.cts.apartment.paymentservice.test;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cts.apartment.paymentservice.model.PaymentEntity;


import static org.junit.Assert.assertEquals;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.json.simple.JSONObject;
import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class IntegrationTest {

	RestTemplate restTemplate;
	String apiUrl = "http://localhost:9558/paymentservice";
   
	public IntegrationTest(){
        restTemplate = new RestTemplate();
    }
	
	
	@Test
	public void test_CreatePayment()
	{
		try
		{
			 HttpHeaders headers = new HttpHeaders();
	         headers.setContentType(MediaType.APPLICATION_JSON);
	         
	         JSONObject obj1 = new JSONObject();
	          obj1.put("aprtid", "4");
	          obj1.put("unitid", "5");
	          obj1.put("ownerid", "2");
	          obj1.put("paymentDate", "2019-01-21");
	          obj1.put("paidamount", "450");
	          HttpEntity<String> request = new HttpEntity<String>(obj1.toJSONString(),headers);
	           // ResponseEntity<String> response=restTemplate.postForObject(postUrl,request,String.class);
	            ResponseEntity<String> response = restTemplate.postForEntity(apiUrl+"/createPayment",request, String.class);
		          
	            String strresp = ""+response.getBody();
	            System.out.println(response.getStatusCode());
	            
	            System.out.println("from server: "+strresp);
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
	}
	
	
	@Test
	public void test_getAllPayments()
	{
		try
		{
			 HttpHeaders headers = new HttpHeaders();
	          
	   		 HttpEntity request = new HttpEntity<String>(headers);
		     ResponseEntity<PaymentEntity[]> response =restTemplate.exchange(new URI(apiUrl+"/viewAllPayment"), HttpMethod.GET, request, PaymentEntity[].class);
		
		List<PaymentEntity> paymentList=Arrays.asList(response.getBody());
		//assertEquals("1",""+ paymentList.get(0).getPayid());
		for(PaymentEntity pm:paymentList)
		{
			System.out.println(pm.getPayid()+" / "+pm.getAprtid());
		}
		}
		catch(Exception q)
		{
			q.printStackTrace();
		}
	}
	
}
