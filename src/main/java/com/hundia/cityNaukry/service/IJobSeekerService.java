package com.hundia.cityNaukry.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.hundia.cityNaukry.pojo.Education;
import com.hundia.cityNaukry.pojo.JobSeeker;
import com.hundia.cityNaukry.pojo.Project;
import com.hundia.cityNaukry.pojo.Skill;

public interface IJobSeekerService {

//	JobSeeker registerJobSeeker(JobSeeker jobSeeker);

	JobSeeker addJobSeeker(JobSeeker newJobSeeker);

	JobSeeker getJobSeekerByEmail(String emailAddress);

	JobSeeker getJobSeekerById(long jobSeekerId);

	Skill addSkill(Long jobSeekerId, Skill skill);

	Education addEducation(Long jobSeekerId, Education education);

	Project addProject(Long jobSeekerId, Project project);

	List<Skill> getSkills(Long jobSeekerId);

	List<Education> getEducation(Long jobSeekerId);

	List<Project> getProjects(Long jobSeekerId);

	JobSeeker updateJobSeeker(JobSeeker existingJobSeeker);

	List<JobSeeker> getAllJobSeekers();

	JobSeeker getJobSeekerByResetToken(String token);


}
