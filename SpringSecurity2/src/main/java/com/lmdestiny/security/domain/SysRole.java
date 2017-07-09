package com.lmdestiny.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

//角色表
@Entity
public class SysRole{
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
    private int id;  
      
    @ManyToOne  
    private SysUser SUser;//角色对应的用户实体  
      
    private String name;//角色名称  
    @OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER,mappedBy="sysRole")
    private List<SysResource> sysResources = new ArrayList<>();
      
    public int getId() {  
        return id;  
    }  
    public void setId(int id) {  
        this.id = id;  
    }  
      
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public SysUser getSUser() {  
        return SUser;  
    }  
    public void setSUser(SysUser sUser) {  
        SUser = sUser;  
    }
	public List<SysResource> getSysResources(){
		return sysResources;
	}
	public void setSysResources(List<SysResource> sysResources){
		this.sysResources = sysResources;
	}  
}
