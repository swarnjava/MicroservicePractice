package com.cts.swarn.exceptionsettings;

public class StudentException extends Exception{
	private String errorMessage;
	 
	public String getErrorMessage() {
		return errorMessage;
	}
	public StudentException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}
	
	public StudentException() {
		super();
	}
}
