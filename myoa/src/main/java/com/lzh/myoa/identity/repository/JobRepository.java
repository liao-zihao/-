package com.lzh.myoa.identity.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lzh.myoa.identity.domain.Job;

public interface JobRepository extends JpaRepository<Job, String>{
	@Query("select new Map(j.code as code ,j.name as name) from Job j")
	public List<Map<String, Object>> findJobs() throws Exception ;
}
