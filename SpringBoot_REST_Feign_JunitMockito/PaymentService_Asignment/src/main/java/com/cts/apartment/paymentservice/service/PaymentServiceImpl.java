package com.cts.apartment.paymentservice.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceException;
import javax.persistence.QueryTimeoutException;

import org.hibernate.JDBCException;
import org.hibernate.hql.internal.QueryExecutionRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cts.apartment.paymentservice.exception.PaymentException;
import com.cts.apartment.paymentservice.model.PaymentEntity;
import com.cts.apartment.paymentservice.repository.PaymentRepository;


@Service
public class PaymentServiceImpl implements PaymentServiceInterface{

	@Autowired
	private PaymentRepository paymentRepo;
	
	
	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Integer createPayment(PaymentEntity pmt) throws PaymentException {
		// TODO Auto-generated method stub
		
		Integer z=0;
	try
		{
		//PaymentEntity pmtobj=paymentRepo.save(pmt);
		PaymentEntity pmtobj=paymentRepo.saveAndFlush(pmt);//return persisted object
		if(pmtobj!=null && pmtobj.getPayid()!=null && pmtobj.getPayid()>0)
			{
				z=pmtobj.getPayid();
			}
		else
			{
				z=0;
			}
		}
		
		
	catch(QueryTimeoutException w3)
		{
			z=0;
			throw new PaymentException("QUERY_TIMED_OUT");
		}
	catch(PersistenceException w4)
		{
			z=0;
			throw new PaymentException("DBPERSISTENT_EXCEPTION");
		}
	catch(RuntimeException r)
	{
		//System.out.println(r.getMessage()+" / "+r.getLocalizedMessage()+" / "+r.getCause());
		z=0;
		throw new PaymentException("RUNTIME_EXCEPTION");
	}
		
		return z;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<PaymentEntity> getAllPaymentList() {
		// TODO Auto-generated method stub
		
		return paymentRepo.getAllPaymentList();
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<PaymentEntity> getPaymentDetailsByApartmentIDandUnitID(Integer aprtmntid, Integer unitid)throws PaymentException {
		// TODO Auto-generated method stub
		List<PaymentEntity> paymentList=paymentRepo.findAll();
		/*Optional<List<PaymentEntity>> list2=Optional.of(paymentList.stream()
                							.filter(n->n.getAprtid()==aprtmntid &&
                										n.getUnitid()==unitid)
                									.collect(Collectors.toList()));*/
		if(paymentList!=null && paymentList.size()>0)
		{
			List<PaymentEntity> newpaymentList=paymentList.stream()
					                           .filter(n->n.getAprtid()==aprtmntid &&
					                                      n.getUnitid()==unitid && n.getIsDelete().equals("N"))
					                           .collect(Collectors.toList());
			if(newpaymentList!=null && newpaymentList.size()>0)
			{
				return newpaymentList;
			}
			else
			{
				throw new PaymentException("EMPTYLIST_EXCEPTION");
			}
		}
		else
		{
			throw new PaymentException("EMPTYLIST_EXCEPTION");
		}
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Double getTotalPaymentByApartmentIDandUnitID(Integer aprtmntid, Integer unitid)throws PaymentException {
		// TODO Auto-generated method stub
		List<PaymentEntity> paymentList=paymentRepo.findAll();
		Double totalPayment=paymentList.stream().filter(n->n.getAprtid()==aprtmntid &&
                                                        n.getUnitid()==unitid && n.getIsDelete().equals("N")).
														mapToDouble(n -> n.getPaidamount().doubleValue()).sum();
		return totalPayment;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public Double getTotalPaymentByApartmentID(Integer aprtmntid)throws PaymentException {
		// TODO Auto-generated method stub
		List<PaymentEntity> paymentList=paymentRepo.findAll();
		Double totalPayment=paymentList.stream().filter(n->n.getAprtid()==aprtmntid && n.getIsDelete().equals("N")).
				                                       mapToDouble(n -> n.getPaidamount().doubleValue()).sum();
		return totalPayment;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Integer deletePayemt(Integer paymentId)throws PaymentException {
		// TODO Auto-generated method stub
		Integer z=0;
		try
		{
			z=paymentRepo.deletePayment(paymentId);
		}
		catch(QueryExecutionRequestException r)
		{
			z=0;
			throw new PaymentException("HQLQUERY_EXCEPTION");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return z;
	}

}
