package com.lmdestiny.security.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.lmdestiny.security.domain.SysResource;
import com.lmdestiny.security.domain.SysRole;
import com.lmdestiny.security.security.repository.SysResourceRepository;
import com.lmdestiny.security.security.repository.SysRoleRepository;

/**
 * 最核心的地方，就是提供某个资源对应的权限定义，即getAttributes方法返回的结果。
 *  此类在初始化时，应该取到所有资源及其对应角色的定义。
 * 
 */
@Service
public class CustomInvocationSecurityMetadataSourceService
		implements
			FilterInvocationSecurityMetadataSource{
	@Autowired
	private SysResourceRepository sysResourceRepository;
	@Autowired
	private SysRoleRepository sysRoleRepository;

	private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

	/*
	 * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。
	 * PostConstruct在构造函数之后执行,init()方法之前执行。
	 */
	// 在web服务器执行之前,得到所有权限
	@PostConstruct
	private void loadResourceDefine(){
		List<SysRole> findAll = sysRoleRepository.findAll();
		List<String> collect = findAll.stream().map(SysRole::getName)
				.collect(Collectors.toList());
		// 应当是资源为key， 权限为value。
		// 资源通常为url， 权限就是那些以ROLE_为前缀的角色。 一个资源可以由多个权限来访问
		resourceMap = new HashMap<String, Collection<ConfigAttribute>>();

		for (String auth : collect) {
			ConfigAttribute ca = new SecurityConfig(auth);
			List<SysResource> sysResources = sysResourceRepository.findBysysRoleName(auth);
			List<String> collect2 = sysResources.stream()
				.map(SysResource::getResourceString)
				.collect(Collectors.toList());
			for(String res:collect2) {
				String url = res;
				//判断资源文件和权限的对应关系，
				//如果已经存在相关的资源url，则要通过该url为key提取出权限集合，将权限增加到权限集合中
				 if (resourceMap.containsKey(url)) {  
	                    Collection<ConfigAttribute> value = resourceMap.get(url);  
	                    value.add(ca);  
	                    resourceMap.put(url, value);  
	                } else {  
	                    Collection<ConfigAttribute> atts = new ArrayList<ConfigAttribute>();  
	                    atts.add(ca);  
	                    resourceMap.put(url, atts);  
	                }  
			}
		}
			
	}
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException{
		 System.out.println("被调用");  
	        // object 是一个URL，被用户请求的url。
	        FilterInvocation filterInvocation = (FilterInvocation) object;  
	        if (resourceMap == null) {  
	            loadResourceDefine();  
	        }  
	        Iterator<String> ite = resourceMap.keySet().iterator();  
	        while (ite.hasNext()) {  
	            String resURL = ite.next();  
	             RequestMatcher requestMatcher = new AntPathRequestMatcher(resURL);  
	                if(requestMatcher.matches(filterInvocation.getHttpRequest())) {  
	                return resourceMap.get(resURL);  
	            }  
	        }  
	        return null;  
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes(){
		// TODO Auto-generated method stub
		return new ArrayList<ConfigAttribute>();
	}

	@Override
	public boolean supports(Class<?> clazz){
		// TODO Auto-generated method stub
		return false;
	}

}
