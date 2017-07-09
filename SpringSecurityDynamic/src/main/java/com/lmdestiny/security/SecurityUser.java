package com.lmdestiny.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lmdestiny.domain.SysRole;
import com.lmdestiny.domain.SysUser;
public class SecurityUser extends SysUser implements UserDetails{

	private static final long serialVersionUID = 885171399733380435L;
	public SecurityUser(SysUser user) {
		if(user != null) {
			this.setId(user.getId());
			this.setUsername(user.getUsername());
			this.setPassword(user.getPassword());
			this.setSysRoles(user.getSysRoles());
		}
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		Collection<GrantedAuthority> authorities = new ArrayList<>();  
        List<SysRole> sysRoles = this.getSysRoles();
        if(sysRoles != null)  
        {  
            for (SysRole role : sysRoles) {  
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.getName());  
                authorities.add(authority);
            }
        }  
        return authorities;  
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
