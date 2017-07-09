package com.lmdestiny.security.passwordCreated;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
public class PasswordCreated{
	@Test
	public void pc(){
		//将密码使用BCrypt加密,或者在http://cmd5.com 网站加密
		BCryptPasswordEncoder bpe =new BCryptPasswordEncoder(5);
		String rawPassword ="111";
		String encode = bpe.encode(rawPassword);
		System.out.println(encode);
		
	}
}
