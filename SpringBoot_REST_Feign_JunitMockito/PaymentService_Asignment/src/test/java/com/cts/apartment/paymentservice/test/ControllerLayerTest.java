package com.cts.apartment.paymentservice.test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.cts.apartment.paymentservice.controller.PaymentController;
import com.cts.apartment.paymentservice.model.PaymentEntity;
import com.cts.apartment.paymentservice.repository.PaymentRepository;
import com.cts.apartment.paymentservice.service.PaymentServiceInterface;


@RunWith(SpringRunner.class)
public class ControllerLayerTest {

	
	@Mock
	PaymentServiceInterface paymentService;
	
	@MockBean
	PaymentRepository paymentMock;//Repository Layer
	
	
	@InjectMocks
	PaymentController paymentController;
	
	
	
	private MockMvc mockMvc;
	
	
	 @Before
     public void setUp() {
      mockMvc = MockMvcBuilders.standaloneSetup(new PaymentController()).build();
     }
	 
	 
	 @Test
	 public void test_viewAllPayment()
	 {
		 try
		 {
			 when(paymentService.getAllPaymentList()).thenReturn(getPaymentList());
			 
			 mockMvc.perform(get("/paymentservice/viewAllPayment")
					 .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$[0].payid", is(101)))
		        .andExpect(jsonPath("$[0].aprtid", is(1)))
		        .andExpect(jsonPath("$[1].unitid", is(2)))
			 	.andExpect(jsonPath("$[1].ownerid", is(1)))
			 	.andExpect(jsonPath("$[1].paidamount", is(620)));
			 
			 verify(paymentService, times(1)).getAllPaymentList();

			 verifyNoMoreInteractions(paymentService);
		 }
		 catch(Exception w)
			{
				w.printStackTrace();
			}
	 }
	 
	 
	 @Test
	 public void test_getPaymentDetailsByApartmentIDandUnitID()
	 {
		 try
		 {
			 when(paymentService.getPaymentDetailsByApartmentIDandUnitID(1,3)).thenReturn(getPaymentList());
			 
			 mockMvc.perform(get("/paymentservice/getPaymentDetailsByApartmentIDandUnitID/1/3")
					 .contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$[0].payid", is(102)))
		        .andExpect(jsonPath("$[0].aprtid", is(1)))
		        .andExpect(jsonPath("$[1].unitid", is(3)))
			 	.andExpect(jsonPath("$[1].ownerid", is(4)))
			 	.andExpect(jsonPath("$[1].paidamount", is(905)));
			 
			 verify(paymentService, times(1)).getPaymentDetailsByApartmentIDandUnitID(1,3);

			 verifyNoMoreInteractions(paymentService);
		 }
		 catch(Exception w)
			{
				w.printStackTrace();
			}
	 }
	 
	 
	 @Test
	 public void test_getSumOfPaymentsByApartmentIDandUnitID()
	 {
		 try
		 {
			 //Mockito.when(paymentMock.findAll()).thenReturn(getPaymentList());//repository mock call
			 when(paymentService.getTotalPaymentByApartmentIDandUnitID(1,3)).thenReturn
			 (
					 905.0
			  );
			 
			 MvcResult result= mockMvc.perform(get("/paymentservice/getPaymentDetailsByApartmentIDandUnitID/1/3")
					 .contentType(MediaType.APPLICATION_JSON))
			 .andDo(print())
	         .andReturn();
			 
			 String content = result.getResponse().getContentAsString();
			 assertEquals("905", content);
			 
			 verify(paymentService, times(1)).getPaymentDetailsByApartmentIDandUnitID(1,3);

			 verifyNoMoreInteractions(paymentService);
		 }
		 catch(Exception w)
			{
				w.printStackTrace();
			}
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
