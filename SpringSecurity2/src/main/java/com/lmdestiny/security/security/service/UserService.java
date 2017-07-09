package com.lmdestiny.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lmdestiny.security.domain.SysUser;
import com.lmdestiny.security.security.repository.UserRepository;

@Service
public class UserService{
	@Autowired
	private UserRepository UserRepository;
	
	public SysUser findByName(String name) {
		return UserRepository.findByName(name);
		
	}
	
}
