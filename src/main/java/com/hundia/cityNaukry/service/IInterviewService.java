package com.hundia.cityNaukry.service;

import java.util.List;

import com.hundia.cityNaukry.pojo.Interview;

public interface IInterviewService {

	Interview scheduleInterview(Interview interview);

	List<Interview> getInterviewsByCompanyId(Long companyId);

	List<Interview> getAllScheduledInterviews();

	void deleteInterviewsByJobSeekerId(Long jobSeekerId);




}
