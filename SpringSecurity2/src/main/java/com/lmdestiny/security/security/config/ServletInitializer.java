package com.lmdestiny.security.security.config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.orm.jpa.support.OpenEntityManagerInViewFilter;

import com.lmdestiny.security.SpringSecurity2Application;

public class ServletInitializer extends SpringBootServletInitializer {  
	  
    @Override  
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {  
        return application.sources(SpringSecurity2Application.class);  
    }  
      
    @Override  
    public void onStartup(ServletContext servletContext)  
    throws ServletException {  
     FilterRegistration.Dynamic openEntityManagerInViewFilter = servletContext.addFilter("openEntityManagerInViewFilter", OpenEntityManagerInViewFilter.class);  
         openEntityManagerInViewFilter.setInitParameter("entityManagerFactoryBeanName","entityManagerFactory");  
         openEntityManagerInViewFilter.addMappingForUrlPatterns(null, false, "/*");  
    super.onStartup(servletContext);  
    }  
}  
