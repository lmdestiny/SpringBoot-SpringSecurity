package com.lmdestiny.security.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired  
    private MyFilterSecurityInterceptor mySecurityFilter;  
      
    @Autowired  
    private CustomUserDetailsService customUserDetailsService;  
      
    @Override  
    public AuthenticationManager authenticationManagerBean() throws Exception {  
       
    return super.authenticationManagerBean();  
       
    }
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http
		.addFilterBefore(mySecurityFilter, FilterSecurityInterceptor.class)//在正确的位置添加我们自定义的过滤器
			.authorizeRequests()
			.antMatchers("/home").permitAll()//home 无需登录验证
			//.anyRequest().authenticated() //其它资源都需要认证
			.antMatchers("/hello").hasRole("ADMIN")
			.and()
				.formLogin()
				.loginPage("/login")//指定登录页为"login"
				//.failureUrl("/login-error")//登录失败
				.successHandler(new LoginSuccessHandler())//登录成功后可使用loginSuccessHandler()存储用户信息，可选。 
			.and()
				.logout()
				.logoutUrl("/logout")
				.logoutSuccessUrl("/home")
				.permitAll()
				.invalidateHttpSession(true)
			.and()
				.rememberMe()////登录后记住用户，下次自动登录,数据库中必须存在名为persistent_logins的表 
				.tokenValiditySeconds(10000);//默认 token 14天有效期
			
	}

	@Autowired
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception{
		//指定密码加密所使用的加密器为passwordEncoder()  
		//需要将密码加密后写入数据库  
		  auth.userDetailsService(customUserDetailsService);  
//		  auth.userDetailsService(|customUserDetailsService).passwordEncoder(passwordEncoder());  
		  //不删除凭据 以便记住用户
	        auth.eraseCredentials(false); 
	}
/*    @Bean  
    public BCryptPasswordEncoder passwordEncoder() {  
        return new BCryptPasswordEncoder(4);  
    }*/ 

	
}
