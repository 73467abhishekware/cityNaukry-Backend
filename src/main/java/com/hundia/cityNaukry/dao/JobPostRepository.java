package com.hundia.cityNaukry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hundia.cityNaukry.pojo.Company;
import com.hundia.cityNaukry.pojo.JobPost;

public interface JobPostRepository extends JpaRepository<JobPost, Long>{

	List<JobPost> findByCompany(Company company);


}
