package com.cts.swarn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cts.swarn.model.StudentEntity;

@Repository
public interface StudentRepository extends JpaRepository<StudentEntity,Integer>{

}
