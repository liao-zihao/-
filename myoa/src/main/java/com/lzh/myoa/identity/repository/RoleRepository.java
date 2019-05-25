package com.lzh.myoa.identity.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.lzh.myoa.identity.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> , JpaSpecificationExecutor<Role>{

}
