package com.lmdestiny.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private MyFilterSecurityInterceptor myFilterSecurityInterceptor;

	 @Bean
	    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
	        return new CustomUserDetailsService();
	    }
	@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.userDetailsService(customUserService()); //user Details Service验证

	    }

	    @Override
	    protected void configure(HttpSecurity http) throws Exception {
	        
	        
	        http
	        	.addFilterBefore(myFilterSecurityInterceptor, FilterSecurityInterceptor.class)
	        	.authorizeRequests()
				.antMatchers("/home").permitAll()//home 无需登录验证
				.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginProcessingUrl("/tologin")
					.loginPage("/login")//指定登录页为"login"
					.permitAll()
					.successHandler( new SuccessHandler())//登录后处理
				.and()
					.logout()
					.logoutUrl("/logout")
					.logoutSuccessUrl("/home")
					.permitAll()
					.invalidateHttpSession(true)//在正确的位置添加我们自定义的过滤器
				.and()
					.rememberMe()//登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表 
					.tokenValiditySeconds(60*60*24*14); //默认是14天
	    }
}