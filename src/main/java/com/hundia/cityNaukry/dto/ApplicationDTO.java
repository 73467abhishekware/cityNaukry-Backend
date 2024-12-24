package com.hundia.cityNaukry.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class ApplicationDTO {
    private long applicationId;
    private JobSeekerDTO jobSeeker;
    private JobPostDTO jobPost;
    private LocalDateTime applicationDate;
    private String applicationStatus;

    // Constructor, Getters, Setters
    public ApplicationDTO() {
		// TODO Auto-generated constructor stub
	}

	public ApplicationDTO(long applicationId, JobSeekerDTO jobSeeker, JobPostDTO jobPost, LocalDateTime applicationDate,
			String applicationStatus) {
		super();
		this.applicationId = applicationId;
		this.jobSeeker = jobSeeker;
		this.jobPost = jobPost;
		this.applicationDate = applicationDate;
		this.applicationStatus = applicationStatus;
	}

	public long getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(long applicationId) {
		this.applicationId = applicationId;
	}

	public JobSeekerDTO getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeekerDTO jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	public JobPostDTO getJobPost() {
		return jobPost;
	}

	public void setJobPost(JobPostDTO jobPost) {
		this.jobPost = jobPost;
	}

	public LocalDateTime getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(LocalDateTime date) {
		this.applicationDate = date;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@Override
	public String toString() {
		return "ApplicationDTO [applicationId=" + applicationId + ", jobSeeker=" + jobSeeker + ", jobPost=" + jobPost
				+ ", applicationDate=" + applicationDate + ", applicationStatus=" + applicationStatus + "]";
	}
    
    
}
