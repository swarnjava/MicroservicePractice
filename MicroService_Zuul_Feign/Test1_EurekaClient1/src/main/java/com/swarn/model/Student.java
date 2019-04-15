package com.swarn.model;

public class Student {
private Integer sid;
private String sname;
private String saddr;

public Student(Integer sid,String sname,String saddr)
{
	this.sid=sid;
	this.sname=sname;
	this.saddr=saddr;
}

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



}
