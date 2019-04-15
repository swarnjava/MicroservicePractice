package com.cts.apartment.paymentservice.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="userinfo")
public class UserInfo implements Serializable{

	
	@Id
	@GeneratedValue
	@Column(name="uiid")
	private Integer uiid;
	
	
	@Column(name="fullname")
	private String fullname;
	
	@Column(name="uaddr")
	private String uaddr;
	
	
	@Column(name="username")
	private String username;
	
	@Column(name="password")
	private String password;
	
	@Column(name="urole")
	private String urole;
	
	
	
	

	public String getUrole() {
		return urole;
	}

	public void setUrole(String urole) {
		this.urole = urole;
	}

	public Integer getUiid() {
		return uiid;
	}

	public void setUiid(Integer uiid) {
		this.uiid = uiid;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getUaddr() {
		return uaddr;
	}

	public void setUaddr(String uaddr) {
		this.uaddr = uaddr;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	@Override
    public boolean equals(Object obj) 
    { 
          
    // checking if both the object references are  
    // referring to the same object. 
    if(this == obj) 
            return true; 
          
       if(obj == null || obj.getClass()!= this.getClass()) 
            return false; 
          
        // type casting of the argument.  
        UserInfo uobj = (UserInfo) obj; 
          
        // comparing the state of argument with  
        // the state of 'this' Object. 
        return (uobj.getUiid()== this.uiid); 
    } 
      
    @Override
    public int hashCode() 
    { 
          
       return this.uiid; 
    } 
	
	
	public String toString() 
    { 
        return uiid + " " + fullname; 
    } 
	
}
