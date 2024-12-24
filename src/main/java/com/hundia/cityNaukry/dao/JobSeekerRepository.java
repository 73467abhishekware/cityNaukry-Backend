package com.hundia.cityNaukry.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hundia.cityNaukry.pojo.JobSeeker;

public interface JobSeekerRepository extends JpaRepository<JobSeeker, Long> {

//	@Query("SELECT js FROM JobSeeker js WHERE LOWER(js.emailAddress) = LOWER(:emailAddress) AND (js.status IS NULL OR js.status = 'jobSeeker')")
	JobSeeker findByEmailAddress(String emailAddress);


}
