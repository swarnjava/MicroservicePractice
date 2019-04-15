package com.swarn.client;

import java.util.List;

import org.json.simple.JSONObject;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import feign.Headers;



@FeignClient(name="paymentservice",url="http://localhost:9552/paymentservice/")
public interface PaymentClient {

	
	@RequestMapping(method = RequestMethod.GET, path = "viewAllPayment")
	public ResponseEntity<List<Object>> getPaymentsData();
	
	
	@RequestMapping(method=RequestMethod.GET,path="getSumOfPaymentsByApartmentIDandUnitID/{aprtmntid}/{unitid}")
	public ResponseEntity<Double> getSumOfPaymentsByApartmentIDandUnitID(@PathVariable("aprtmntid") Integer aprtmntid,@PathVariable("unitid") Integer unitid);
	
	
	@RequestMapping(method=RequestMethod.POST,path="/createPayment")
	@Headers("Content-Type: application/json")
	
	public ResponseEntity<String> createPayment(@RequestHeader("Authorization") String authtoken,@RequestBody JSONObject json);
}
