package com.hundia.cityNaukry.pojo;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.util.Date;

@Entity
public class Application {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long applicationId;

    @ManyToOne
    @JoinColumn(name = "jobSeekerId")
    private JobSeeker jobSeeker;

    @ManyToOne
    @JoinColumn(name = "job_post_id")
    private JobPost jobPost;

    private Date applicationDate;
    private String applicationStatus;  // Pending, Accepted, Rejected


  public Application() {
	// TODO Auto-generated constructor stub
}


public Application(long applicationId, JobSeeker jobSeeker, JobPost jobPost, Date applicationDate,
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


public JobSeeker getJobSeeker() {
	return jobSeeker;
}


public void setJobSeeker(JobSeeker jobSeeker) {
	this.jobSeeker = jobSeeker;
}


public JobPost getJobPost() {
	return jobPost;
}


public void setJobPost(JobPost jobPost) {
	this.jobPost = jobPost;
}


public Date getApplicationDate() {
	return applicationDate;
}


public void setApplicationDate(Date applicationDate) {
	this.applicationDate = applicationDate;
}


public String getApplicationStatus() {
	return applicationStatus;
}


public void setApplicationStatus(String applicationStatus) {
	this.applicationStatus = applicationStatus;
}
  
  
}
