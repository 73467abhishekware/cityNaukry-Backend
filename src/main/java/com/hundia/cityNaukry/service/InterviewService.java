package com.hundia.cityNaukry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.dao.InterviewRepository;
import com.hundia.cityNaukry.pojo.Interview;

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






}
