package com.lmdestiny.security.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class persistent_logins{
	@Id
	@Column(name="series",length=64,nullable=false)
	private String series;
	@Column(name="username",length=64,nullable=false)
	private String username;
	@Column(name="token",length=64,nullable=false)
	private String token;
	@Temporal(TemporalType.DATE)
	private Date last_used = new Date();
	public String getSeries(){
		return series;
	}
	public void setSeries(String series){
		this.series = series;
	}
	public String getUsername(){
		return username;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getToken(){
		return token;
	}
	public void setToken(String token){
		this.token = token;
	}
	public Date getLast_used(){
		return last_used;
	}
	public void setLast_used(Date last_used){
		this.last_used = last_used;
	}
	
}
