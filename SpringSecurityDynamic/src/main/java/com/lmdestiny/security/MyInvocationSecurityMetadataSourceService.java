package com.lmdestiny.security;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Service;

import com.lmdestiny.domain.Permission;
import com.lmdestiny.domain.SysRole;
import com.lmdestiny.repository.PermissionRepository;

@Service
public class MyInvocationSecurityMetadataSourceService  implements
        FilterInvocationSecurityMetadataSource {

    @Autowired
    private PermissionRepository permissionRepository;

    private HashMap<String, Collection<ConfigAttribute>> resourceMap =null;

    /**
     * 加载权限表中所有权限
     */
   // @PostConstruct
    public void loadResourceDefine(){
    	List<Permission> findAll = permissionRepository.findAll();
    	resourceMap = new HashMap<>();
    	for(Permission p: findAll) {
    		System.out.println(p.getUrl());
    		List<SysRole> sysRoles = p.getSysRoles();
    		List<ConfigAttribute> configAttributes = new ArrayList<>();
    		for(SysRole s:sysRoles) {
    			System.out.println(s.getName());
    			ConfigAttribute ca = new SecurityConfig(s.getName());
    			configAttributes.add(ca);
    		}
    		System.out.println("---------");
    		resourceMap.put(p.getUrl(), configAttributes);
    	}
    }

//此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
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
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
