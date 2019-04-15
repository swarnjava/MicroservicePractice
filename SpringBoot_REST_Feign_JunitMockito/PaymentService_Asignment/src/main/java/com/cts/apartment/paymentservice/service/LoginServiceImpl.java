package com.cts.apartment.paymentservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cts.apartment.paymentservice.model.UserInfo;
import com.cts.apartment.paymentservice.repository.UserRepository;

@Service
public class LoginServiceImpl implements LoginServiceIntrfc{

	@Autowired
	private UserRepository urepo;

	@Override
	@Transactional(propagation=Propagation.REQUIRED,readOnly=true)
	public UserInfo findUserByUserNameandPassword(String unm, String pwd) {
		// TODO Auto-generated method stub
		return urepo.findUserByUserNameandPassword(unm, pwd);
		
	}
	
	
	
}
