package com.lmdestiny.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lmdestiny.domain.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
