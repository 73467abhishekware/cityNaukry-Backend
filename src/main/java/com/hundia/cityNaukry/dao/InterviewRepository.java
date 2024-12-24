package com.hundia.cityNaukry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hundia.cityNaukry.pojo.Interview;

public interface InterviewRepository extends JpaRepository<Interview, Long> {

	   @Query("SELECT i FROM Interview i WHERE i.jobPost.company.CompanyId = :companyId")
	    List<Interview> findInterviewsByCompanyId(Long companyId);



}
