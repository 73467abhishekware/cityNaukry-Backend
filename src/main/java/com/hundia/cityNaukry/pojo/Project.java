package com.hundia.cityNaukry.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String projectName;
    private String description;
    private String projectLink;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

  
public Project() {
	// TODO Auto-generated constructor stub
}


public Project(Long id, String projectName, String description, String projectLink, JobSeeker jobSeeker) {
	super();
	this.id = id;
	this.projectName = projectName;
	this.description = description;
	this.projectLink = projectLink;
	this.jobSeeker = jobSeeker;
}


public Long getId() {
	return id;
}


public void setId(Long id) {
	this.id = id;
}


public String getProjectName() {
	return projectName;
}


public void setProjectName(String projectName) {
	this.projectName = projectName;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public String getProjectLink() {
	return projectLink;
}


public void setProjectLink(String projectLink) {
	this.projectLink = projectLink;
}


public JobSeeker getJobSeeker() {
	return jobSeeker;
}


public void setJobSeeker(JobSeeker jobSeeker) {
	this.jobSeeker = jobSeeker;
}


@Override
public String toString() {
	return "Project [id=" + id + ", projectName=" + projectName + ", description=" + description + ", projectLink="
			+ projectLink + ", jobSeeker=" + jobSeeker + "]";
}


 
}
