package com.lmdestiny.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lmdestiny.domain.SysUser;
import com.lmdestiny.repository.SysUserRepository;
//自定义userDetailService
@Service
public class CustomUserDetailsService implements UserDetailsService{
	@Autowired
	private SysUserRepository sysUserRepository;
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException{
		SysUser user = sysUserRepository.findByUsername(username);
		if(user ==null) {
			throw new UsernameNotFoundException(username+"not found");
		}
		SecurityUser u = new SecurityUser(user);
		return u;
	}

}
