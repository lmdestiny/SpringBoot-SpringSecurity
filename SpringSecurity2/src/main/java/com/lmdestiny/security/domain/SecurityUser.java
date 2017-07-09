package com.lmdestiny.security.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUser extends SysUser implements UserDetails{

	private static final long serialVersionUID = 2096562491845328182L;
	
	public SecurityUser(SysUser suser) {  
        if(suser != null)  
        {  
            this.setId(suser.getId());  
            this.setName(suser.getName());  
            this.setPassword(suser.getPassword());  
            this.setDob(suser.getDob());  
            this.setSysRoles(suser.getSysRoles());  
        }         
    }  
      
    @Override  
    public Collection<? extends GrantedAuthority> getAuthorities() {  
          
        Collection<GrantedAuthority> authorities = new ArrayList<>();  
        Set<SysRole> userRoles = this.getSysRoles(); 
          
        if(userRoles != null)  
        {  
            for (SysRole role : userRoles) {  
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());  
                authorities.add(authority);  
            }  
        }  
        return authorities;  
    }  


	@Override
	public String getUsername(){
		// TODO Auto-generated method stub
		return super.getName();
	}

	@Override
	public boolean isAccountNonExpired(){
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked(){
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired(){
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled(){
		// TODO Auto-generated method stub
		return true;
	}

}
