package com.cts.apartment.paymentservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.cts.apartment.paymentservice.model.PaymentEntity;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentEntity, Integer>{

	
	
	@Query("SELECT p FROM PaymentEntity p WHERE p.isDelete='N'")
	public List<PaymentEntity> getAllPaymentList();
	
	@Modifying
	@Query("UPDATE PaymentEntity p SET p.isDelete='Y' WHERE p.payid=:paymentId")
	public Integer deletePayment(@Param("paymentId") Integer paymentId);
	
	/*@Query("SELECT p FROM Person p WHERE LOWER(p.lastName) = LOWER(:lastName)")
    public List<Person> find(@Param("lastName") String lastName);*/
}
