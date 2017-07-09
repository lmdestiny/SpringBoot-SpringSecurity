package com.lmdestiny.security;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.lmdestiny.security.security.config.MyFilterSecurityInterceptor;

@SpringBootApplication(exclude=MyFilterSecurityInterceptor.class)
public class SpringSecurity2Application {
	
	private static final Logger log = LoggerFactory.getLogger(SpringSecurity2Application.class);  
    @PostConstruct  
     public void initApplication() throws IOException {  
         log.info("Running with Spring profile(s) : {}");   
    }  
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurity2Application.class, args);
	}
}
