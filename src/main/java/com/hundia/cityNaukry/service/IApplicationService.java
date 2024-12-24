package com.hundia.cityNaukry.service;

import java.util.List;

import com.hundia.cityNaukry.dto.ApplicationDTO;
import com.hundia.cityNaukry.pojo.Application;

public interface IApplicationService {

	void saveApplication(Application application);

	List<Application> getApplicantsByJobPost_Id(long jobPostId);

	List<Application> getApplicationsByJobSeekerId(long jobSeekerId);

	List<ApplicationDTO> getAllApplicationsWithJobPostAndJobSeeker();

	Application getApplicationById(long applicationId);

	void deleteApplication(long applicationId);

}
