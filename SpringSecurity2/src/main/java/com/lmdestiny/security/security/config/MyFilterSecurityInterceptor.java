package com.lmdestiny.security.security.config;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import com.lmdestiny.security.security.service.CustomInvocationSecurityMetadataSourceService;
@Component
public class MyFilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{

	 @Autowired  
	    private CustomInvocationSecurityMetadataSourceService  mySecurityMetadataSource;  
	      
	    @Autowired  
	    private CustomAccessDecisionManager myAccessDecisionManager;  
	      
	    @Autowired  
	    private AuthenticationManager authenticationManager;  
	      
	      
	    @PostConstruct  
	    public void init(){  
	        super.setAuthenticationManager(authenticationManager);  
	        super.setAccessDecisionManager(myAccessDecisionManager);  
	    }  
	      
	    public void doFilter( ServletRequest request, ServletResponse response, FilterChain chain)  
	    throws IOException, ServletException{  
	        FilterInvocation fi = new FilterInvocation( request, response, chain );  
	        invoke(fi);  
	          
	    }  
	  
	      
	    public Class<? extends Object> getSecureObjectClass(){  
	        return FilterInvocation.class;  
	    }  
	  
	      
	    public void invoke( FilterInvocation fi ) throws IOException, ServletException{  
	        System.out.println("filter..........................");  
	        InterceptorStatusToken  token = super.beforeInvocation(fi);  
	        try{  
	            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
	        }finally{  
	            super.afterInvocation(token, null);  
	        }  
	          
	    }  
	          
	      
	    @Override  
	    public SecurityMetadataSource obtainSecurityMetadataSource(){  
	        System.out.println("filtergergetghrthetyetyetyetyj");  
	        return this.mySecurityMetadataSource;  
	    }  
	      
	    public void destroy(){  
	        System.out.println("filter===========================end");  
	    }  
	    public void init( FilterConfig filterconfig ) throws ServletException{  
	        System.out.println("filter===========================");  
	    }  
	}  