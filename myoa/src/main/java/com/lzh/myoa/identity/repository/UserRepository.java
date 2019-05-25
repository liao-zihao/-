package com.lzh.myoa.identity.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.lzh.myoa.identity.domain.User;

public interface UserRepository extends JpaRepository<User,String>,JpaSpecificationExecutor<User> {
	@Query("select u.userId from User u where u.userId not in(select u.userId from User u inner join u.roles r where r.id = ?1)")
	List<String> getRolesUsers(Long id);
    
	@Query("select u.userId from User u inner join u.roles r where r.id = ?1")
	List<String> findRoleUsers(Long id);
}
