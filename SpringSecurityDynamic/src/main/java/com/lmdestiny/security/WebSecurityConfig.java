package com.lmdestiny.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;


@EnableWebSecurity
//@Order(1)  配置多HttpSecurity时指定优先级,不指定放到最后
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	 @Autowired
	 private MyFilterSecurityInterceptor myFilterSecurityInterceptor;
	 
	 @Bean
	    UserDetailsService customUserService(){ //注册UserDetailsService 的bean
	        return new CustomUserDetailsService();
	    }
	 //通过让passwordencoder为bean自定义密码如何编码,不是必须的
	 //相对于MD5+salt更加安全,但开销更大,高并发时不建议使用,
	 //存储时密码长度不能低于64
	 // 推荐网站 http://cmd5.com/
	 @Bean
	 public BCryptPasswordEncoder passwordEncoder() {
	 	return new BCryptPasswordEncoder(5);
	 }
	 //remember-me相关
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 @Bean
	 JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl() {
		 JdbcTokenRepositoryImpl jdbcTokenRepositoryImpl = new JdbcTokenRepositoryImpl();
		 jdbcTokenRepositoryImpl.setJdbcTemplate(jdbcTemplate);
		 jdbcTokenRepositoryImpl.setCreateTableOnStartup(true);
		 return jdbcTokenRepositoryImpl;
	 }
	@Override
	    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	       auth.userDetailsService(customUserService())//user Details Service验证
	       	.passwordEncoder(passwordEncoder()); 
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
					////默认为TokenBasedRememberMeServices,即返回一个token字符串,下次登录时对比
					.key("lmdestiny")
					.rememberMeServices(new PersistentTokenBasedRememberMeServices("lmdestiny",customUserService(),jdbcTokenRepositoryImpl()))
					.tokenValiditySeconds(60*60*24*14); //默认是14天
	    }
}
