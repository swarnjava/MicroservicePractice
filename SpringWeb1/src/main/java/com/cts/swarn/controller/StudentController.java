package com.cts.swarn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cts.swarn.constants.AppConstant;
import com.cts.swarn.exceptionsettings.StudentException;
import com.cts.swarn.model.StudentEntity;
import com.cts.swarn.service.StudentService;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class StudentController {

	@Autowired
	private StudentService studentService;
	
	
	
	// inject via application.properties
			@Value("${welcome.message:test}")
			private String message = "Hello World";

			@RequestMapping("/")
			public String welcome(Map<String, Object> model) {
				model.put("message", this.message);
				return "welcome";
			}
	
	
	
	
	
	
	
	
	@RequestMapping("/openstudentform")
	public ModelAndView openstudentform()
	{
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("student", new StudentEntity());
		
		doCommonTask(mav);
		mav.setViewName("student");
		return mav;
	}
	
	
	@RequestMapping("/openstudentLogin")
	public ModelAndView openstudentLogin()
	{
		ModelAndView mav=new ModelAndView();
		
		mav.addObject("student", new StudentEntity());
		
		
		mav.setViewName("login");
		return mav;
	}
	
	
	
	@RequestMapping("/checkLogin")
	public ModelAndView checkLogin(HttpServletRequest request,@ModelAttribute("student")StudentEntity student)
	{
		
		ModelAndView mav=new ModelAndView();
		
		
		String jwtToken = Jwts.builder().
				 setSubject("LoginAuthentication").
				 claim("roles", "user").
				 claim("username", "swarn").
				 claim("scope", "self groups/admins").
				 claim("code", "S1001").
				 setIssuedAt(new Date()).
				 setId("10001")
		         .signWith(SignatureAlgorithm.HS256,AppConstant.secretkey).compact();
		
		
		mav.setViewName("student");
		
		mav.addObject("jwtToken","Bearer "+jwtToken);
		mav.addObject("student", new StudentEntity());
		doCommonTask(mav);
		return mav;
	
	}
	
	@RequestMapping("/saveStudent")
	public ModelAndView saveStudent(@ModelAttribute("student")StudentEntity student,HttpServletRequest request)throws StudentException
	{
		ModelAndView mav=new ModelAndView();
		
		String token=request.getParameter("authtoken");
		if(token!=null && token.startsWith("Bearer "))
		{
			
			String tokenstr = token.substring(7);
		try
		{
			Claims claims = Jwts.parser().setSigningKey(AppConstant.secretkey).parseClaimsJws(tokenstr).getBody();
			String roles = (String) claims.get("roles");
			 String username = (String) claims.get("username");
			 String scope = (String) claims.get("scope");
			 String scode = (String) claims.get("code");
			 System.out.println("at JwtAuthorisationFilter : "+roles+" / "+username+" / "+scope+" / "+scode);
	
			
				Integer z=studentService.createStudent(student);
				if(z>0)
				{
					mav.addObject("message", "Create Successfully");
				}
				else
				{
					mav.addObject("message", "Not Created. Please Try again");
				}
		}
			catch(Exception w)
			{
				w.printStackTrace();
			}
		}
		else
		{
			mav.addObject("message", "No Token Found");
		}
		mav.setViewName("student");
		mav.addObject("student", new StudentEntity());
		doCommonTask(mav);
		return mav;
	}
	
	
	@RequestMapping("/editStudent")
	public ModelAndView saveStudent(HttpServletRequest request)throws StudentException
	{
		ModelAndView mav=new ModelAndView();
		String sid=request.getParameter("sid");
		if(sid!=null && sid.isEmpty()==false)
		{
			StudentEntity std=studentService.getStudentById(Integer.parseInt(sid));
			mav.addObject("student", std);
		}
		
		mav.setViewName("student");
		
		doCommonTask(mav);
		return mav;
	}
	
	
	private ModelAndView doCommonTask(ModelAndView mav)
	{
		List<StudentEntity> studentList=new ArrayList<StudentEntity>();
		studentList=studentService.getAllStudents();
		mav.addObject("stdList", studentList);
		
		return mav;
	}
	
	
	
}
