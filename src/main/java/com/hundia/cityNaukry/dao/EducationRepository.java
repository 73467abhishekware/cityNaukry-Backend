package com.hundia.cityNaukry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hundia.cityNaukry.pojo.Education;

public interface EducationRepository  extends JpaRepository<Education, Long> {

	List<Education> findByJobSeeker_JobSeekerId(Long jobSeekerId);

}