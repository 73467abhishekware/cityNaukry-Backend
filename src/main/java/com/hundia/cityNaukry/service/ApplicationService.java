package com.hundia.cityNaukry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.dao.ApplicationRepository;
import com.hundia.cityNaukry.dto.ApplicationDTO;
import com.hundia.cityNaukry.pojo.Application;

@Service
public class ApplicationService implements IApplicationService{

	 @Autowired
	    private ApplicationRepository applicationRepository;
	
	@Override
	public void saveApplication(Application application) {
		 applicationRepository.save(application);
		
	}

	@Override
	public List<Application> getApplicantsByJobPost_Id(long jobPostId) {
		
	        return applicationRepository.findByJobPost_Id(jobPostId);
	    
	}

	@Override
	public List<Application> getApplicationsByJobSeekerId(long jobSeekerId) {
		  return applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
	}

	@Override
	public List<ApplicationDTO> getAllApplicationsWithJobPostAndJobSeeker() {
		 List<Application> applications = applicationRepository.findAll();

		    // Map to DTOs that include job post and job seeker details
		    List<ApplicationDTO> applicationDTOs = new ArrayList<>();
		    for (Application application : applications) {
		        ApplicationDTO applicationDTO = new ApplicationDTO();
		        applicationDTOs.add(applicationDTO);
		    }

		    return applicationDTOs;
	}

	@Override
	public Application getApplicationById(long applicationId) {
		  return applicationRepository.findById(applicationId).orElse(null);
	}

	@Override
	public void deleteApplication(long applicationId) {
		  applicationRepository.deleteById(applicationId);
		
	}

}
