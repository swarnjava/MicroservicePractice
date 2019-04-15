package com.cts.apartment.paymentservice.service;

import com.cts.apartment.paymentservice.model.UserInfo;

public interface LoginServiceIntrfc {
public UserInfo findUserByUserNameandPassword(String unm,String pwd);
}
