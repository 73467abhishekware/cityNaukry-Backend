package com.hundia.cityNaukry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hundia.cityNaukry.pojo.Project;

public interface ProjectRepository  extends JpaRepository<Project, Long> {

	List<Project> findByJobSeeker_JobSeekerId(Long jobSeekerId);

}
