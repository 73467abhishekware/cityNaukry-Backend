package com.hundia.cityNaukry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.dao.InterviewRepository;
import com.hundia.cityNaukry.pojo.Interview;

import jakarta.transaction.Transactional;

@Service
public class InterviewService implements IInterviewService {

	@Autowired
	private InterviewRepository interviewRepo;
	
	@Override
	public Interview scheduleInterview(Interview interview) {
		   return interviewRepo.save(interview);
	}

	@Override
	public List<Interview> getInterviewsByCompanyId(Long companyId) {
		return interviewRepo.findInterviewsByCompanyId(companyId);
	}

	@Override
	public List<Interview> getAllScheduledInterviews() {
		 return interviewRepo.findAll();
	}

	@Override
	@Transactional
	public void deleteInterviewsByJobSeekerId(Long jobSeekerId) {
	    // Delete all interviews related to the job seeker
	    interviewRepo.deleteByJobSeekerJobSeekerId(jobSeekerId);
	}





}
