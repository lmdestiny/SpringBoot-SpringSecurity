package com.lmdestiny.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.lmdestiny.security.domain.SecurityUser;
import com.lmdestiny.security.domain.SysUser;
import com.lmdestiny.security.security.service.UserService;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserService userService;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException{
		SysUser user = userService.findByName(username);
		if(user == null) {
			throw new UsernameNotFoundException(username+"not found");
		}
		SecurityUser su = new SecurityUser(user);
		return su;
	}

}
