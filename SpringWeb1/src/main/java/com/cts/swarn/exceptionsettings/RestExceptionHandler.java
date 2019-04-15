package com.cts.swarn.exceptionsettings;

import java.sql.SQLException;

import javax.persistence.QueryTimeoutException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;

@ControllerAdvice
public class RestExceptionHandler {

	//custom exception
	/*@ExceptionHandler(GeneralException.class)
	public ResponseEntity<ErrorResponse> exceptionGeneralExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.PRECONDITION_FAILED.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
	}
	*/
	//custom exception
	@ExceptionHandler(StudentException.class)
	public ResponseEntity<ErrorResponse> exceptionStudentExceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("JDBC_Exception"))
		{
		error.setErrorCode(500);
		error.setMessage("JDBC Exception Occurs");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("ENTITY_NOT_FOUND"))
		{
		error.setErrorCode(404);
		error.setMessage("Entity Not Found");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("QUERY_TIMED_OUT"))
		{
		error.setErrorCode(408);
		error.setMessage("Query Times Out");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.REQUEST_TIMEOUT);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("FIELD_BLANK_EXCEPTION"))
		{
		error.setErrorCode(400);
		error.setMessage("All Fields are Mandatory");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("NUMBER_FORMAT_EXCEPTION"))
		{
		error.setErrorCode(400);
		error.setMessage("sid must be Integer");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("NON_UNIQUE_RESULT"))
		{
		error.setErrorCode(412);
		error.setMessage("Non Unique Result");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.PRECONDITION_FAILED);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("NEGATIVE_ID"))
		{
		error.setErrorCode(400);
		error.setMessage("ID can't be negative");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		else if(ex.getMessage()!=null && ex.getMessage().equalsIgnoreCase("NULL_URI_PARAMETER"))
		{
		error.setErrorCode(400);
		error.setMessage("Uri Parameter is Empty");
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
		}
		else
		{
			error.setErrorCode(500);
			error.setMessage("Unknown Exception.Please Try Again");
			return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	
	//Global Exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		if(ex instanceof SQLException)
		{
		error.setErrorCode(101);
		error.setMessage("Database Related Exception.");
		}
		else if(ex instanceof NumberFormatException)
		{
		error.setErrorCode(102);
		error.setMessage("sid field must be Integer.Number Format Exception");
		}
		else if(ex instanceof QueryTimeoutException)
		{
		error.setErrorCode(103);
		error.setMessage("Query Timed out");
		}
		else if(ex instanceof InvalidFormatException)
		{
		error.setErrorCode(100);
		error.setMessage("Invalid Format Exception");
		}
		else
		{
			error.setErrorCode(HttpStatus.BAD_REQUEST.value());
			error.setMessage("The request could not be understood by the server due to malformed syntax.");

		}
		
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	
	
}
