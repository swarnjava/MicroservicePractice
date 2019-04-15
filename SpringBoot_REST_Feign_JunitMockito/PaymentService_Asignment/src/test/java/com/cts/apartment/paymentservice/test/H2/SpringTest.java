package com.cts.apartment.paymentservice.test.H2;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import com.cts.apartment.paymentservice.main.SpringExecutor;
import com.cts.apartment.paymentservice.model.PaymentEntity;
import com.cts.apartment.paymentservice.repository.PaymentRepository;
import com.cts.apartment.paymentservice.service.PaymentServiceInterface;

@RunWith(SpringRunner.class)
@SpringBootTest(classes =SpringExecutor.class)
@ActiveProfiles("test")
public class SpringTest {

	
	@Autowired
	private PaymentServiceInterface paymentService;
	
	@Autowired
	private PaymentRepository paymentRepo;
	
	@Test
	public void test1()
	{
		try
		{
			
	    Double d=paymentService.getTotalPaymentByApartmentID(1);
		System.out.println("$$$$$$$$$$$$$$$$$$$: "+d);
		}
		catch(Exception w)
		{
			w.printStackTrace();
		}
	}
	
}
