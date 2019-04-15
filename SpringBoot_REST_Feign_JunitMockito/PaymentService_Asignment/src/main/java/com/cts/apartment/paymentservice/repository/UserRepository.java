package com.cts.apartment.paymentservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cts.apartment.paymentservice.model.UserInfo;

public interface UserRepository extends JpaRepository<UserInfo, Integer>{

	@Query("SELECT u FROM UserInfo u WHERE u.username = :uname and u.password=:pwd")
    public UserInfo findUserByUserNameandPassword(@Param("uname") String uname,@Param("pwd") String pwd);
}
