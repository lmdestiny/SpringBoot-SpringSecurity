package com.lmdestiny.security.security.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lmdestiny.security.domain.SysUser;
public interface UserRepository extends JpaRepository<SysUser,Integer>{

	SysUser findByName(String userName);
}
