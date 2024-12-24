package com.hundia.cityNaukry.dto;

public class JobPostDTO {
    private long jobPostId;
    private String title;
    private String description;
    private String responsibilities;
    private String companyName;
    private String location;
    private String salaryRange;
    private String qualifications;
    private int experienceRequired;
    private String employmentType;

 
public JobPostDTO() {
	// TODO Auto-generated constructor stub
}




public JobPostDTO(long jobPostId, String title, String description, String responsibilities, String companyName,
		String location, String salaryRange, String qualifications, int experienceRequired, String employmentType) {
	super();
	this.jobPostId = jobPostId;
	this.title = title;
	this.description = description;
	this.responsibilities = responsibilities;
	this.companyName = companyName;
	this.location = location;
	this.salaryRange = salaryRange;
	this.qualifications = qualifications;
	this.experienceRequired = experienceRequired;
	this.employmentType = employmentType;
}




public long getJobPostId() {
	return jobPostId;
}


public void setJobPostId(long jobPostId) {
	this.jobPostId = jobPostId;
}


public String getTitle() {
	return title;
}


public void setTitle(String title) {
	this.title = title;
}


public String getDescription() {
	return description;
}


public void setDescription(String description) {
	this.description = description;
}


public String getResponsibilities() {
	return responsibilities;
}


public void setResponsibilities(String responsibilities) {
	this.responsibilities = responsibilities;
}


public String getCompanyName() {
	return companyName;
}


public void setCompanyName(String companyName) {
	this.companyName = companyName;
}


public String getLocation() {
	return location;
}


public void setLocation(String location) {
	this.location = location;
}


public String getSalaryRange() {
	return salaryRange;
}


public void setSalaryRange(String salaryRange) {
	this.salaryRange = salaryRange;
}


public String getQualifications() {
	return qualifications;
}


public void setQualifications(String qualifications) {
	this.qualifications = qualifications;
}


public int getExperienceRequired() {
	return experienceRequired;
}


public void setExperienceRequired(int experienceRequired) {
	this.experienceRequired = experienceRequired;
}


public String getEmploymentType() {
	return employmentType;
}


public void setEmploymentType(String employmentType) {
	this.employmentType = employmentType;
}




@Override
public String toString() {
	return "JobPostDTO [jobPostId=" + jobPostId + ", title=" + title + ", description=" + description
			+ ", responsibilities=" + responsibilities + ", companyName=" + companyName + ", location=" + location
			+ ", salaryRange=" + salaryRange + ", qualifications=" + qualifications + ", experienceRequired="
			+ experienceRequired + ", employmentType=" + employmentType + "]";
}  




}
