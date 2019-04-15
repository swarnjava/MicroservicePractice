package com.cts.swarn.service;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityNotFoundException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.QueryTimeoutException;

import org.hibernate.JDBCException;
import org.hibernate.exception.JDBCConnectionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cts.swarn.exceptionsettings.StudentException;
import com.cts.swarn.model.StudentEntity;
import com.cts.swarn.repository.StudentRepository;
import com.fasterxml.jackson.core.JsonParseException;


@Service
public class StudentServiceImpl implements StudentService{
	
	
	@Autowired
	private StudentRepository studentRepo;

	@Override
	@Transactional(propagation=Propagation.REQUIRED)
	public Integer createStudent(StudentEntity std) throws StudentException {
		// TODO Auto-generated method stub
		Integer z=0;
		try
			{
			if(std.getSname()==null || std.getSname().isEmpty()==true||
					std.getSaddr()==null||std.getSaddr().isEmpty()==true||
					std.getScontact()==null||std.getScontact().isEmpty()==true)
			{
				throw new StudentException("FIELD_BLANK_EXCEPTION");
			}
			
			else
			 {
			
					StudentEntity stdnt=studentRepo.save(std);
					if(stdnt!=null && stdnt.getSid()!=null && stdnt.getSid()>0)
					{
						z=1;
					}
					else
					{
						z=0;
					}
			 }
				
			}
		
		catch(JDBCException w1)
		{
			z=0;
			throw new StudentException("JDBC_Exception");
		}
		catch(EntityNotFoundException w2)
		{
			z=0;
			throw new StudentException("ENTITY_NOT_FOUND");
		}
		catch(QueryTimeoutException w2)
		{
			z=0;
			throw new StudentException("QUERY_TIMED_OUT");
		}
		/*catch(Exception w2)
		{
			z=0;
			throw new StudentException("Unknown_Exception");
		}*/
		
		return z;
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public StudentEntity getStudentById(Integer sid) throws StudentException {
		// TODO Auto-generated method stub
		try
		{
			return studentRepo.findOne(sid);
		}
		catch(QueryTimeoutException e1)
		{
			throw new StudentException("QUERY_TIMED_OUT");
		}
		catch(EntityNotFoundException e2)
		{
			throw new StudentException("ENTITY_NOT_FOUND");
		}
		catch(NonUniqueResultException e2)
		{
			throw new StudentException("NON_UNIQUE_RESULT");
		}
		
		
		
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public List<StudentEntity> getAllStudents() {
		// TODO Auto-generated method stub
		return studentRepo.findAll();
	}

}
