package com.lmdestiny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmdestiny.domain.SysUser;

public interface SysUserRepository extends JpaRepository<SysUser, Integer>{

	SysUser findByUsername(String username);

}
