package com.cts.swarn.service;

import com.cts.swarn.exceptionsettings.StudentException;
import com.cts.swarn.model.StudentEntity;
import java.util.*;

public interface StudentService {
public Integer createStudent(StudentEntity std)throws StudentException;
public StudentEntity getStudentById(Integer sid)throws StudentException;
public List<StudentEntity> getAllStudents();
}
