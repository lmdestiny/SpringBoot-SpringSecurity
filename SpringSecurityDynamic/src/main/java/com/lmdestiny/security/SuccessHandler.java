package com.lmdestiny.security;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.lmdestiny.domain.SysUser;

public class SuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler{

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException{
		//登录成功后获得用户信息
		//SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		SysUser user = (SysUser)authentication.getPrincipal();
		System.out.println(user.toString());
		//输出所具有的权限Role
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			System.out.println(grantedAuthority.toString());
		}
		super.onAuthenticationSuccess(request, response, authentication);
	}

}
