package com.lmdestiny.security.security.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmdestiny.security.domain.SysResource;

public interface SysResourceRepository extends JpaRepository<SysResource, Integer> {

	List<SysResource> findBysysRoleName(String auth);

}
