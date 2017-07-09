package com.lmdestiny.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

//用户表
@Entity
public class SysUser{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String username;
	private String password;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<SysRole> sysRoles = new ArrayList<>();
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getPassword(){
		return password;
	}
	public void setPassword(String password){
		this.password = password;
	}
	public List<SysRole> getSysRoles(){
		return sysRoles;
	}
	public void setSysRoles(List<SysRole> sysRoles){
		this.sysRoles = sysRoles;
	}
	@Override
	public String toString(){
		return "SysUser [id=" + id + ", username=" + username + ", password="
				+ password + ", sysRoles=" + sysRoles + "]";
	}
	

}
