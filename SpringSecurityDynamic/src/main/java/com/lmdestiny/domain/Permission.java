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

@Entity
public class Permission{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String url;
	@ManyToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	private List<SysRole> sysRoles = new ArrayList<>();
	
	public Integer getId(){
		return id;
	}
	public void setId(Integer id){
		this.id = id;
	}
	public String getUrl(){
		return url;
	}
	public void setUrl(String url){
		this.url = url;
	}
	public List<SysRole> getSysRoles(){
		return sysRoles;
	}
	public void setSysRoles(List<SysRole> sysRoles){
		this.sysRoles = sysRoles;
	}

}
