package com.cts.apartment.paymentservice.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="payment_tab")
public class PaymentEntity implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue
	@Column(name="PAYID")
	private Integer payid;
	
	@Column(name="APRTID")
	private Integer aprtid;
	
	@Column(name="UNITID")
	private Integer unitid;
	
	
	@Column(name="OWNERID")
	private Integer ownerid;
	
	@Column(name="DATE_OF_PAYMENT")
	@Temporal(TemporalType.DATE)
	private Date paymentDate;
	
	
	@Column(name="AMOUNT", columnDefinition="Decimal(10,2)")
	private BigDecimal paidamount;
	
	
	@Column(name="ISDELETE")
	private String isDelete;
	
	
	public PaymentEntity()
	{
		//Default Constructor
	}


	public Integer getPayid() {
		return payid;
	}


	public void setPayid(Integer payid) {
		this.payid = payid;
	}


	public Integer getAprtid() {
		return aprtid;
	}


	public void setAprtid(Integer aprtid) {
		this.aprtid = aprtid;
	}


	public Integer getUnitid() {
		return unitid;
	}


	public void setUnitid(Integer unitid) {
		this.unitid = unitid;
	}


	public Integer getOwnerid() {
		return ownerid;
	}


	public void setOwnerid(Integer ownerid) {
		this.ownerid = ownerid;
	}


	public Date getPaymentDate() {
		return paymentDate;
	}


	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}


	public BigDecimal getPaidamount() {
		return paidamount;
	}


	public void setPaidamount(BigDecimal paidamount) {
		this.paidamount = paidamount;
	}


	public String getIsDelete() {
		return isDelete;
	}


	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
	
	
	@Override
    public boolean equals(Object obj) 
    { 
          
    // checking if both the object references are  
    // referring to the same object. 
    if(this == obj) 
            return true; 
          
       if(obj == null || obj.getClass()!= this.getClass()) 
            return false; 
          
        // type casting of the argument.  
        PaymentEntity pmobj = (PaymentEntity) obj; 
          
        // comparing the state of argument with  
        // the state of 'this' Object. 
        return (pmobj.payid == this.payid && pmobj.aprtid == this.aprtid); 
    } 
      
    @Override
    public int hashCode() 
    { 
          
       return this.payid; 
    } 
      
	
	
	
	
	public String toString() 
    { 
        return aprtid + " " + unitid + " " + ownerid + " " + paidamount + " " + paymentDate; 
    } 
}
