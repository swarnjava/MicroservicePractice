package com.cts.swarn.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cts.swarn.exceptionsettings.StudentException;
import com.cts.swarn.model.StudentEntity;
import com.cts.swarn.service.StudentService;
import com.fasterxml.jackson.core.JsonParseException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/studentService")
@Api(value="Api Controller Services", description="Student Service At PCF")
public class ApiController {

	@Autowired
	private StudentService studentService;
	
	
	
	@ApiOperation(value = "Create New Entity")
/*	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 400, message = "Malformed Syntax Exception"),
	        @ApiResponse(code = 101, message = "JDBC Exception Occurs"),
	        @ApiResponse(code = 108, message = "Entity Not Found"),
	        @ApiResponse(code = 103, message = "Query Times Out"),
	        @ApiResponse(code = 104, message = "Unknown Exception.Please Try Again")
	}
	)*/
	@CrossOrigin
	@RequestMapping(value="/create",method=RequestMethod.POST,consumes = "application/json")
	public ResponseEntity<String> createData(@RequestBody StudentEntity stud,HttpServletRequest request)throws StudentException
	{
		if(stud==null)
			{
				throw new StudentException("EntityNotFound_Exception");
			}
		else
		{
			
			Integer z=studentService.createStudent(stud);
			if(z>0)
				{
					return new ResponseEntity<String>("Successfully Created",HttpStatus.OK);
				}
			else
				{
				return new ResponseEntity<String>("Not Created Try Again Please",HttpStatus.OK);
				}
			
			
			
		}
	}
	
	
	@ApiOperation(value = "Get Single Student By ID")
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 500, message = "Internal Server Error"),
	        @ApiResponse(code = 404, message = "Entity Not Found Exception"),
	        @ApiResponse(code = 408, message = "Query TimeOut Exception"),
	        @ApiResponse(code = 400, message = "Bad Request"),
	        @ApiResponse(code = 412, message = "Non Unique Result,Pre Condition Failed")
	       
	        
	}
	)
	@CrossOrigin
	@RequestMapping(value="/getStudentByID",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getStudentByID(HttpServletRequest request) throws StudentException
	{
		
		
		String stuid=request.getParameter("sid");
		if(null!=stuid && stuid.isEmpty()==false)
		{
			try
			{
				if(Integer.parseInt(stuid)>0)
				{
					StudentEntity std=new StudentEntity();
					std=studentService.getStudentById(Integer.parseInt(stuid));
						if(std!=null && std.getSid()>0)
						  	{
								return new ResponseEntity<StudentEntity>(std,HttpStatus.OK);
						  	}
						else
							{
								return new ResponseEntity<String>("No Student Found",HttpStatus.OK);
							}
				}
				else
				{
					throw new StudentException("NEGATIVE_ID");
				}
			}
			catch(NumberFormatException n)
			{
				throw new StudentException("NUMBER_FORMAT_EXCEPTION");
			}
			
		}
		else
		{
			throw new StudentException("NULL_URI_PARAMETER");
		}
		
		
	
	}
	
	
	
	@ApiOperation(value = "Get All Students")
	/*@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully Done"),
	        @ApiResponse(code = 701, message = "Database Related Exception"),
	        @ApiResponse(code = 702, message = "Entity Not Found Exception"),
	        @ApiResponse(code = 703, message = "Query TimeOut Exception"),
	        @ApiResponse(code = 704, message = "All Fields are Mandatory"),
	        @ApiResponse(code = 705, message = "SID must be Integer"),
	        @ApiResponse(code = 706, message = "Please Try Again")
	}
	)*/
	@CrossOrigin
	@RequestMapping(value="/getAllStudents",method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<StudentEntity>> getAllStudents(HttpServletRequest request)
	{
		List<StudentEntity> studentList=new ArrayList<StudentEntity>();
		studentList=studentService.getAllStudents();
		return new ResponseEntity<List<StudentEntity>>(studentList,HttpStatus.OK);
	}
	
}
