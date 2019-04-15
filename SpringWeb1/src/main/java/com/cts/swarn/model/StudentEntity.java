package com.cts.swarn.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;

@Entity
@Table(name="student")
public class StudentEntity {

	
	@Id
	@GeneratedValue
	@Column(name="sid")
	@ApiModelProperty(notes = "The database generated Student ID")
	private Integer sid;
	
	@Column(name="sname",length=20)
	@ApiModelProperty(notes = "Student Name : should be max length 20")
	private String sname;
	
	@Column(name="saddr",length=500)
	@ApiModelProperty(notes = "Student Address : should be max length 500")
	private String saddr;
	
	
	@Column(name="scontact",length=10)
	@ApiModelProperty(notes = "Student Address : should be max length 10")
	private String scontact;


	public Integer getSid() {
		return sid;
	}


	public void setSid(Integer sid) {
		this.sid = sid;
	}


	public String getSname() {
		return sname;
	}


	public void setSname(String sname) {
		this.sname = sname;
	}


	public String getSaddr() {
		return saddr;
	}


	public void setSaddr(String saddr) {
		this.saddr = saddr;
	}


	public String getScontact() {
		return scontact;
	}


	public void setScontact(String scontact) {
		this.scontact = scontact;
	}
	
	
}
