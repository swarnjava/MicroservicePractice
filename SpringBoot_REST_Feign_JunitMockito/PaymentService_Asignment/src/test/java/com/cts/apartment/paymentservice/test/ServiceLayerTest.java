package com.cts.apartment.paymentservice.test;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.template.PathBasedTemplateAvailabilityProvider;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.cts.apartment.paymentservice.exception.PaymentException;
import com.cts.apartment.paymentservice.model.PaymentEntity;
import com.cts.apartment.paymentservice.repository.PaymentRepository;
import com.cts.apartment.paymentservice.service.PaymentServiceInterface;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RunWith(SpringRunner.class)
//@PropertySource("persistence-generic-entity.properties")
@SpringBootTest
public class ServiceLayerTest {

	
	@MockBean
	PaymentRepository paymentMock;//Repository Layer
	
	
	@Autowired
	private PaymentServiceInterface paymentService;
	
	
	
	
	
	/*@Test
	public void test_createPayment() throws ParseException, PaymentException
	{
		PaymentEntity pm=getSinglePaymentEntity();
		paymentService.createPayment(pm);
		//Mockito.when(paymentMock.save(pm)).thenReturn(getSinglePaymentEntity());//repository mock call
		//assertEquals("101",""+paymentService.createPayment(pm));// service layer call
		
	}
	*/
	
	
	
	
	@Test
	public void test_viewAllPayment() throws ParseException,PaymentException {
		
			//PaymentEntity pm=getSinglePaymentEntity();
			//paymentService.createPayment(pm);
		
		Mockito.when(paymentMock.getAllPaymentList()).thenReturn(getPaymentList());//repository mock call
		
		assertEquals("101",""+paymentService.getAllPaymentList().get(0).getPayid());// service layer call
	}
	
	
	@Test
	public void test_getPaymentDetailsByApartmentIDandUnitID()throws PaymentException, ParseException
	{
		
		Mockito.when(paymentMock.findAll()).thenReturn(getPaymentList());//repository mock call
		assertEquals("1",""+paymentService.getPaymentDetailsByApartmentIDandUnitID(1,2).size());// service layer call
	}
	
	@Test
	public void test_getSumOfPaymentsByApartmentIDandUnitID()throws PaymentException, ParseException
	{
		
		Mockito.when(paymentMock.findAll()).thenReturn(getPaymentList());//repository mock call
		assertEquals("405.0",""+paymentService.getTotalPaymentByApartmentIDandUnitID(1,2));// service layer call
	}
	
	
	
	@Test
	public void test_getSumOfPaymentsByApartmentID()throws PaymentException, ParseException
	{
		
		Mockito.when(paymentMock.findAll()).thenReturn(getPaymentList());//repository mock call
		assertEquals("750.0",""+paymentService.getTotalPaymentByApartmentID(2));// service layer call
	}
	
	
	private List<PaymentEntity> getPaymentList() throws ParseException
	{
		List<PaymentEntity> pymtList=new ArrayList<PaymentEntity>();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date myDate = new Date();
		String dtrDate=formatter.format(myDate);
		
		PaymentEntity pm1=new PaymentEntity();
		pm1.setPayid(101);
		pm1.setAprtid(1);
		pm1.setUnitid(2);
		pm1.setOwnerid(3);
		
		pm1.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(dtrDate));
		pm1.setPaidamount(new BigDecimal(405));
		pm1.setIsDelete("N");
		
		PaymentEntity pm2=new PaymentEntity();
		pm2.setPayid(102);
		pm2.setAprtid(1);
		pm2.setUnitid(3);
		pm2.setOwnerid(4);
		
		pm2.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(dtrDate));
		pm2.setPaidamount(new BigDecimal(905));
		pm2.setIsDelete("N");
		
		
		PaymentEntity pm3=new PaymentEntity();
		pm3.setPayid(103);
		pm3.setAprtid(2);
		pm3.setUnitid(2);
		pm3.setOwnerid(7);
		
		pm3.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(dtrDate));
		pm3.setPaidamount(new BigDecimal(750));
		pm3.setIsDelete("N");
		
		
		pymtList.add(pm1);
		pymtList.add(pm2);
		pymtList.add(pm3);
		
		return pymtList;
	}
	
	
	private PaymentEntity getSinglePaymentEntity() throws ParseException
	{
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
		Date myDate = new Date();
		String dtrDate=formatter.format(myDate);
		
		PaymentEntity pm1=new PaymentEntity();
		pm1.setPayid(101);
		pm1.setAprtid(1);
		pm1.setUnitid(2);
		pm1.setOwnerid(3);
		
		pm1.setPaymentDate(new SimpleDateFormat("yyyy-mm-dd").parse(dtrDate));
		pm1.setPaidamount(new BigDecimal(405));
		pm1.setIsDelete("N");
		
		
		return pm1;
	}
	
}
