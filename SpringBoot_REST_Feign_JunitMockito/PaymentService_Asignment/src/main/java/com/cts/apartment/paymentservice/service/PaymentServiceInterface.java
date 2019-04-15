package com.cts.apartment.paymentservice.service;

import java.math.BigDecimal;
import java.util.List;

import com.cts.apartment.paymentservice.exception.PaymentException;
import com.cts.apartment.paymentservice.model.PaymentEntity;

public interface PaymentServiceInterface {
	
		public Integer createPayment(PaymentEntity pmt)throws PaymentException;
		public List<PaymentEntity> getAllPaymentList();
        public List<PaymentEntity> getPaymentDetailsByApartmentIDandUnitID(Integer aprtmntid,Integer unitid)throws PaymentException;
        public Double getTotalPaymentByApartmentIDandUnitID(Integer aprtmntid,Integer unitid)throws PaymentException;
        public Double getTotalPaymentByApartmentID(Integer aprtmntid)throws PaymentException;
        public Integer deletePayemt(Integer paymentId)throws PaymentException;
}
