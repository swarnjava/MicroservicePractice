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

@RestController
public class ClientController {

	
	@Autowired
	private PaymentClient pc;
	
	
	@RequestMapping("/test")
	public void testFeign() throws JsonProcessingException, ParseException
	{
		ResponseEntity<List<Object>> dataResp1=pc.getPaymentsData();
		JSONParser parser = new JSONParser();
		if(dataResp1!=null)
		{
			List<Object> dataList=dataResp1.getBody();
			ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
			
				for(Object object:dataList)
				{
					String stringToParse = ow.writeValueAsString(object);
					
					JSONObject jsonobj = (JSONObject) parser.parse(stringToParse);
					System.out.println(jsonobj.get("payid")+" / "+jsonobj.get("aprtid")+" / "+jsonobj.get("unitid"));
				}
			
			System.out.println(dataList);
		}
		
		
		ResponseEntity<Double> dataResp2=pc.getSumOfPaymentsByApartmentIDandUnitID(1, 2);
		if(dataResp2!=null)
		{
			Double d=dataResp2.getBody();
			System.out.println(d);
		}
		
		
		 JSONObject request1 = new JSONObject();
         request1.put("aprtid","8");
         request1.put("unitid", "3");
         request1.put("ownerid", "5");
         request1.put("paymentDate", "2019-02-14");
         request1.put("paidamount", "985.00");
		String authtoken="Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c";
		ResponseEntity<String> dataResp3=pc.createPayment(authtoken,request1);
		if(dataResp3!=null)
		{
			String resp=dataResp3.getBody();
			System.out.println(resp);
		}
	}
}
