package com.lmdestiny.security.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

//用户表
@Entity
public class SysUser implements java.io.Serializable{
	 /**
	 * 
	 */
	private static final long serialVersionUID = -8076460506784540263L;
	@Id  
     @GeneratedValue(strategy = GenerationType.IDENTITY)  
     @Column(name = "id", unique = true, nullable = false)  
     private Integer id;  
     private String name; //用户名  
     private String password;//用户密码  
     @Temporal(TemporalType.DATE)  
     private Date dob;//时间  
     @OneToMany(mappedBy="SUser",fetch=FetchType.EAGER)
     private Set<SysRole> SysRoles = new HashSet<SysRole>(0);// 所对应的角色集合  

     public SysUser() {  
     }  

     public SysUser(String name,String password, Date dob, Set<SysRole> SysRoles) {  
         this.name = name;  
         this.password = password;  
         this.dob = dob;  
         this.SysRoles = SysRoles;  
     }

	public Integer getId(){
		return id;
	}

	public void setId(Integer id){
		this.id = id;
	}

	public String getName(){
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	public String getPassword(){
		return password;
	}

	public void setPassword(String password){
		this.password = password;
	}

	public Date getDob(){
		return dob;
	}

	public void setDob(Date dob){
		this.dob = dob;
	}

	public Set<SysRole> getSysRoles(){
		return SysRoles;
	}

	public void setSysRoles(Set<SysRole> sysRoles){
		SysRoles = sysRoles;
	}  
}
