package com.lmdestiny.security.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity  
public class SysResource {  
        @Id  
        @GeneratedValue(strategy=GenerationType.IDENTITY)  
        private int id;  
          
        private String resourceString;//url  
          
        private String methodName;//资源所对应的方法名  
          
        private String methodPath;//资源所对应的包路径  
        @ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
        private SysRole sysRole;
          
        public int getId() {  
            return id;  
        }  
  
        public void setId(int id) {  
            this.id = id;  
        }  
  
        public String getResourceString() {  
            return resourceString;  
        }  
  
        public void setResourceString(String resourceString) {  
            this.resourceString = resourceString;  
        }  
  
  
        public String getMethodName() {  
            return methodName;  
        }  
  
        public void setMethodName(String methodName) {  
            this.methodName = methodName;  
        }  
  
        public String getMethodPath() {  
            return methodPath;  
        }  
  
        public void setMethodPath(String methodPath) {  
            this.methodPath = methodPath;  
        }
          
}  
