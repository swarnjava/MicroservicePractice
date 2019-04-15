package com.cts.apartment.paymentservice.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.apartment.paymentservice.exception.PaymentException;
import com.cts.apartment.paymentservice.model.PaymentEntity;
import com.cts.apartment.paymentservice.service.PaymentServiceInterface;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.*;



@RestController
@RequestMapping("/paymentservice")
@Api(value="Payment Services", description="Payment Service")
public class PaymentController {

	
	@Autowired
	private PaymentServiceInterface paymentService;
	
	
	@CrossOrigin
	@ApiOperation(value = "Create New Payment")
		@ApiResponses(value = {
		        @ApiResponse(code = 200, message = "Successfully Done"),
		        @ApiResponse(code = 500, message = "Internal Server error"),
		        @ApiResponse(code = 404, message = "Not Found"),
		        @ApiResponse(code = 408, message = "Request TimeOut"),
		        @ApiResponse(code = 400, message = "Bad Request"),
		        @ApiResponse(code = 412, message = "Pre-Condition Failed"),
		        @ApiResponse(code = 204, message = "No Content")
		}
		)
	@RequestMapping(value="/createPayment",method=RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<String> createData(@RequestBody PaymentEntity pmt,HttpServletRequest request,@RequestHeader HttpHeaders headers)throws PaymentException
	{
		
        String authheaderstr=""+headers.get("authorization").toString();
        System.out.println("authentiation header: "+authheaderstr);

		if(null==pmt)
			{
				throw new PaymentException("ENTITY_NOT_FOUND");
			}
		else if(null==pmt.getUnitid()||
				null==pmt.getAprtid()||
				null==pmt.getOwnerid()||
				null==pmt.getPaymentDate()||
				null==pmt.getPaidamount())
		{
			throw new PaymentException("NULL_MANDATORYFIELD_EXCEPTION");
		}
		else if( (String.valueOf(pmt.getPaidamount()).contains(".") && String.valueOf(pmt.getPaidamount()).length()>8)||
				(!String.valueOf(pmt.getPaidamount()).contains(".") && String.valueOf(pmt.getPaidamount()).length()>5))//actepted limit: 99999.50 or 99999
		{
			throw new PaymentException("PAYMENT_AMOUNT_LARGE");
		}
		else
		{
			pmt.setIsDelete("N");
			Integer z=paymentService.createPayment(pmt);
			
			if(z>0)
				{
					return new ResponseEntity<String>("Successfully Created",HttpStatus.OK);
				}
			else
				{
				return new ResponseEntity<String>("Not Created Try Again Please",HttpStatus.OK);
				}
			
			
			
		}
	}
	
	
	
	@CrossOrigin
	@ApiOperation(value = "Update Existing Payment")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 500, message = "Internal Server error"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 408, message = "Request TimeOut"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 412, message = "Pre-Condition Failed"),
	        @ApiResponse(code = 204, message = "No Content")
	}
	)
	@RequestMapping(value="/updatePayment",method=RequestMethod.PUT,consumes = "application/json")
	public ResponseEntity<String> updatePayment(@RequestBody PaymentEntity pmt,HttpServletRequest request)throws PaymentException
	{
		if(pmt==null)
			{
				throw new PaymentException("ENTITY_NOT_FOUND");
			}
		else if(null==pmt.getPayid()||
				pmt.getPayid()<1||
				null==pmt.getUnitid()||
				null==pmt.getAprtid()||
				null==pmt.getOwnerid()||
				null==pmt.getPaymentDate()||
				null==pmt.getPaidamount())
		{
			throw new PaymentException("NULL_MANDATORYFIELD_EXCEPTION");
		}
		else if( (String.valueOf(pmt.getPaidamount()).contains(".") && String.valueOf(pmt.getPaidamount()).length()>8)||
				(!String.valueOf(pmt.getPaidamount()).contains(".") && String.valueOf(pmt.getPaidamount()).length()>5))//actepted limit: 99999.50 or 99999
		{
			throw new PaymentException("PAYMENT_AMOUNT_LARGE");
		}
		else
		{
			pmt.setIsDelete("N");
			Integer z=paymentService.createPayment(pmt);
			
			if(z>0)
				{
					return new ResponseEntity<String>("Successfully Updated",HttpStatus.OK);
				}
			else
				{
				return new ResponseEntity<String>("Not Created Try Again Please",HttpStatus.OK);
				}
			
			
			
		}
	}
	
	
	@CrossOrigin
	@ApiOperation(value = "View All Payments")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 500, message = "Internal Server error"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 408, message = "Request TimeOut"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 412, message = "Pre-Condition Failed"),
	        @ApiResponse(code = 204, message = "No Content")
	}
	)
	@RequestMapping(value="/viewAllPayment",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PaymentEntity>> getAllPaymentData()
	{
		List<PaymentEntity> paymentList=new ArrayList<PaymentEntity>();
		
		paymentList=paymentService.getAllPaymentList();
		return new ResponseEntity<List<PaymentEntity>>(paymentList,HttpStatus.OK);
	}
	
	@CrossOrigin
	@ApiOperation(value = "Fetch Payment Details By Apartment ID & Unit ID")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 500, message = "Internal Server error"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 408, message = "Request TimeOut"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 412, message = "Pre-Condition Failed"),
	        @ApiResponse(code = 204, message = "No Content")
	}
	)
	@RequestMapping(value="/getPaymentDetailsByApartmentIDandUnitID/{aprtmntid}/{unitid}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PaymentEntity>> getPaymentDetailsByApartmentIDandUnitID
	(@PathVariable(name = "aprtmntid") Integer aprtmntid,
	 @PathVariable(name = "unitid") Integer unitid)throws PaymentException
	{
		if(aprtmntid!=null && aprtmntid>0 &&
				unitid!=null && unitid>0)
		{
		List<PaymentEntity> paymentList=paymentService.getPaymentDetailsByApartmentIDandUnitID(aprtmntid, unitid);
		if(paymentList!=null && paymentList.size()>0)
			{
				return new ResponseEntity<List<PaymentEntity>>(paymentList,HttpStatus.OK);
			}
		else
			{
			throw new PaymentException("EMPTYLIST_EXCEPTION");
			}
		}
		else
		{
			throw new PaymentException("WRONG_CREDENTIAL_EXCEPTION");	
		}
	}
	
	
	@CrossOrigin
	@ApiOperation(value = "Get Sum of Payments by Apartment ID & Unit ID")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 500, message = "Internal Server error"),
	        @ApiResponse(code = 404, message = "Not Found"),
	        @ApiResponse(code = 408, message = "Request TimeOut"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 412, message = "Pre-Condition Failed"),
	        @ApiResponse(code = 204, message = "No Content")
	}
	)
	@RequestMapping(value="/getSumOfPaymentsByApartmentIDandUnitID/{aprtmntid}/{unitid}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getSumOfPaymentsByApartmentIDandUnitID
	(@PathVariable(name = "aprtmntid") Integer aprtmntid,
	 @PathVariable(name = "unitid") Integer unitid)throws PaymentException
	{
		    Double totalPmtAmount=0.00;
		
			totalPmtAmount=paymentService.getTotalPaymentByApartmentIDandUnitID(aprtmntid, unitid);
		
			return new ResponseEntity<Double>(totalPmtAmount,HttpStatus.OK);
			
		
	}
	
	
	@CrossOrigin
	@ApiOperation(value = "Get Sum of Payments by Apartment ID only")
	@RequestMapping(value="/getSumOfPaymentsByApartmentID/{aprtmntid}",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Double> getSumOfPaymentsByApartmentID
	(@PathVariable(name = "aprtmntid") Integer aprtmntid
	)throws PaymentException
	{
		    Double totalPmtAmount=0.00;
		
			totalPmtAmount=paymentService.getTotalPaymentByApartmentID(aprtmntid);
		
			return new ResponseEntity<Double>(totalPmtAmount,HttpStatus.OK);
			
		
	}
	
	@CrossOrigin
	@ApiOperation(value = "Delete a Payment")
	@RequestMapping(value="/deletePayment/{paymentid}",method=RequestMethod.GET)
	public ResponseEntity<String> deletePayment
	(@PathVariable(name = "paymentid") Integer paymentId
	)throws PaymentException
	{
		    
		
			Integer z=0;
			z=paymentService.deletePayemt(paymentId);
		if(z>0)
		{
			return new ResponseEntity<String>("Successfully Deleted",HttpStatus.OK);
		}
		else
		{
			return new ResponseEntity<String>("Not Deleted.Please Try Again",HttpStatus.OK);
		}
			
			
		
	}
}
