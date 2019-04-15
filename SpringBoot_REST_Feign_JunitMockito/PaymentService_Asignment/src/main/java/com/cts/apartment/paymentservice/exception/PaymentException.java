package com.cts.apartment.paymentservice.exception;

public class PaymentException extends Exception{
	private String errorMessage;
	 
	public String getErrorMessage() {
		return errorMessage;
	}
	public PaymentException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public PaymentException() {
		super();
	}
}
