package com.hundia.cityNaukry.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.hundia.cityNaukry.dao.ApplicationRepository;
import com.hundia.cityNaukry.dao.EducationRepository;
import com.hundia.cityNaukry.dao.JobSeekerRepository;
import com.hundia.cityNaukry.dao.ProjectRepository;
import com.hundia.cityNaukry.dao.SkillRepository;
import com.hundia.cityNaukry.pojo.Education;
import com.hundia.cityNaukry.pojo.JobSeeker;
import com.hundia.cityNaukry.pojo.Project;
import com.hundia.cityNaukry.pojo.Skill;

@Service
public class JobSeekerService implements IJobSeekerService {

	@Autowired
	private JobSeekerRepository jobSeekerRepo;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private EducationRepository educationRepository;
	
	@Autowired
	private ProjectRepository projectRepository;

	@Autowired
	private ApplicationRepository applictionRepo;
//    @Override
//    public JobSeeker registerJobSeeker(JobSeeker jobSeeker) {
//        return jobSeekerRepo.save(jobSeeker);
//    }



	@Override
	public JobSeeker addJobSeeker(JobSeeker newJobSeeker) {
		
		return jobSeekerRepo.save(newJobSeeker);
	}



	@Override
	public JobSeeker getJobSeekerByEmail(String emailAddress) {
		return jobSeekerRepo.findByEmailAddress(emailAddress);
	}



	@Override
	public JobSeeker getJobSeekerById(long jobSeekerId) {
		  return jobSeekerRepo.findById(jobSeekerId)
		            .orElseThrow();
	}



	@Override
	public Skill addSkill(Long jobSeekerId, Skill skill) {
		 JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow();
		    skill.setJobSeeker(jobSeeker);
		    return skillRepository.save(skill);
	}



	@Override
	public Education addEducation(Long jobSeekerId, Education education) {
		JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow();
	    education.setJobSeeker(jobSeeker);
	    return educationRepository.save(education);
	}



	@Override
	public Project addProject(Long jobSeekerId, Project project) {
		 JobSeeker jobSeeker = jobSeekerRepo.findById(jobSeekerId).orElseThrow();
		    project.setJobSeeker(jobSeeker);
		    return projectRepository.save(project);
	}



	@Override
	public List<Skill> getSkills(Long jobSeekerId) {
		try {
	        // Fetch skills associated with the given JobSeeker ID
	        return skillRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
	    } catch (Exception e) {
	        // Handle any exceptions and log the error if needed
	        throw new RuntimeException("Error retrieving skills for JobSeeker ID: " + jobSeekerId, e);
	    }
	}



	@Override
	public List<Education> getEducation(Long jobSeekerId) {
		try {
	        // Fetch education details associated with the given JobSeeker ID
	        return educationRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
	    } catch (Exception e) {
	        // Handle any exceptions and log the error if needed
	        throw new RuntimeException("Error retrieving education details for JobSeeker ID: " + jobSeekerId, e);
	    }
	}



	@Override
	public List<Project> getProjects(Long jobSeekerId) {
	    try {
	        // Fetch projects associated with the given JobSeeker ID
	        return projectRepository.findByJobSeeker_JobSeekerId(jobSeekerId);
	    } catch (Exception e) {
	        // Handle any exceptions and log the error if needed
	        throw new RuntimeException("Error retrieving projects for JobSeeker ID: " + jobSeekerId, e);
	    }
	}



	@Override
	public JobSeeker updateJobSeeker(JobSeeker existingJobSeeker) {
		 return jobSeekerRepo.save(existingJobSeeker);
	}



	@Override
	public List<JobSeeker> getAllJobSeekers() {
		return jobSeekerRepo.findAll();
	}



	@Override
	public JobSeeker getJobSeekerByResetToken(String token) {
		   return null; 
	}



	@Override
	public void deleteJobSeeker(Long jobSeekerId) {
		jobSeekerRepo.deleteById(jobSeekerId);
	}



	

	
}
