package com.hundia.cityNaukry.service;

import java.util.List;

import com.hundia.cityNaukry.dto.ApplicationDTO;
import com.hundia.cityNaukry.pojo.Application;
import com.hundia.cityNaukry.pojo.JobPost;
import com.hundia.cityNaukry.pojo.JobSeeker;

public interface IApplicationService {

	void saveApplication(Application application);

	List<Application> getApplicantsByJobPost_Id(long jobPostId);

	List<Application> getApplicationsByJobSeekerId(long jobSeekerId);

	List<ApplicationDTO> getAllApplicationsWithJobPostAndJobSeeker();

	Application getApplicationById(long applicationId);

	void deleteApplication(long applicationId);

	void deleteApplicationsByJobSeekerId(Long jobSeekerId);

	Application getApplicationByJobSeekerAndJobPost(JobSeeker jobSeeker, JobPost jobPost);

}
