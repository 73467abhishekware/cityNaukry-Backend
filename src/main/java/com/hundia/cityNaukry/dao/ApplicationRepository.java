package com.hundia.cityNaukry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.hundia.cityNaukry.pojo.Application;
import com.hundia.cityNaukry.pojo.JobPost;
import com.hundia.cityNaukry.pojo.JobSeeker;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long> {

    @Query("SELECT a FROM Application a WHERE a.jobPost.id = :jobPostId")
    List<Application> findByJobPost_Id(long jobPostId);


	List<Application> findByJobSeeker_JobSeekerId(long jobSeekerId);


	Application findByJobSeekerAndJobPost(JobSeeker jobSeeker, JobPost jobPost);


}
