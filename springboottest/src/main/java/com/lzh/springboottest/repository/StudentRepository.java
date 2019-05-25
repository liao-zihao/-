package com.lzh.springboottest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lzh.springboottest.bean.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {
	
}
