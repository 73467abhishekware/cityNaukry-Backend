package com.hundia.cityNaukry.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.dao.ApplicationRepository;
import com.hundia.cityNaukry.dto.ApplicationDTO;
import com.hundia.cityNaukry.pojo.Application;
import com.hundia.cityNaukry.pojo.JobPost;
import com.hundia.cityNaukry.pojo.JobSeeker;

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

	@Override
	public void deleteApplicationsByJobSeekerId(Long jobSeekerId) {
		try {
	        // Fetch all applications related to the given jobSeekerId
	        List<Application> applications = applicationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);

	        // If applications exist, delete them
	        if (!applications.isEmpty()) {
	        	applicationRepository.deleteAll(applications);
	        }
	    } catch (Exception e) {
	        // Log the exception or handle it accordingly
	        throw new RuntimeException("Error deleting applications for JobSeeker ID: " + jobSeekerId, e);
	    }
		
	}

	@Override
	public Application getApplicationByJobSeekerAndJobPost(JobSeeker jobSeeker, JobPost jobPost) {
		return applicationRepository.findByJobSeekerAndJobPost(jobSeeker, jobPost);
	}

}
