package com.hundia.cityNaukry.pojo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class JobSeeker {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long jobSeekerId;
	private String firstName;
	private String lastName;
	private String emailAddress;

	// Step 2: Account Information
	private String password;
	private String confirmPassword;
	private String phoneNumber;
	private String location;

	// Step 3: Resume Upload
	private String resumePath; // Path or URL of the uploaded resume

	// Step 4: Profile Picture
	private String profilePicturePath;
	
	private String status = "jobSeeker";
	
	  @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	    private List<Skill> skills = new ArrayList<>();

	    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	    private List<Education> education = new ArrayList<>();

	    @OneToMany(mappedBy = "jobSeeker", cascade = CascadeType.ALL)
	    private List<Project> projects = new ArrayList<>();
	    
	    
	    private String resetToken;
	    private LocalDateTime resetTokenExpiry;
	
  public JobSeeker() {
	// TODO Auto-generated constructor stub
}


public JobSeeker(long jobSeekerId, String firstName, String lastName, String emailAddress, String password,
		String confirmPassword, String phoneNumber, String location, String resumePath, String profilePicturePath,
		String status, List<Skill> skills, List<Education> education, List<Project> projects) {
	super();
	this.jobSeekerId = jobSeekerId;
	this.firstName = firstName;
	this.lastName = lastName;
	this.emailAddress = emailAddress;
	this.password = password;
	this.confirmPassword = confirmPassword;
	this.phoneNumber = phoneNumber;
	this.location = location;
	this.resumePath = resumePath;
	this.profilePicturePath = profilePicturePath;
	this.status = status;
	this.skills = skills;
	this.education = education;
	this.projects = projects;
}


public long getJobSeekerId() {
	return jobSeekerId;
}


public void setJobSeekerId(long jobSeekerId) {
	this.jobSeekerId = jobSeekerId;
}


public String getFirstName() {
	return firstName;
}


public void setFirstName(String firstName) {
	this.firstName = firstName;
}


public String getLastName() {
	return lastName;
}


public void setLastName(String lastName) {
	this.lastName = lastName;
}


public String getEmailAddress() {
	return emailAddress;
}


public void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
}


public String getPassword() {
	return password;
}


public void setPassword(String password) {
	this.password = password;
}


public String getConfirmPassword() {
	return confirmPassword;
}


public void setConfirmPassword(String confirmPassword) {
	this.confirmPassword = confirmPassword;
}


public String getPhoneNumber() {
	return phoneNumber;
}


public void setPhoneNumber(String phoneNumber) {
	this.phoneNumber = phoneNumber;
}


public String getLocation() {
	return location;
}


public void setLocation(String location) {
	this.location = location;
}


public String getResumePath() {
	return resumePath;
}


public void setResumePath(String resumePath) {
	this.resumePath = resumePath;
}


public String getProfilePicturePath() {
	return profilePicturePath;
}


public void setProfilePicturePath(String profilePicturePath) {
	this.profilePicturePath = profilePicturePath;
}


public String getStatus() {
	return status;
}


public void setStatus(String status) {
	this.status = status;
}


public List<Skill> getSkills() {
	return skills;
}


public void setSkills(List<Skill> skills) {
	this.skills = skills;
}


public List<Education> getEducation() {
	return education;
}


public void setEducation(List<Education> education) {
	this.education = education;
}


public List<Project> getProjects() {
	return projects;
}


public void setProjects(List<Project> projects) {
	this.projects = projects;
}

public String getResetToken() {
    return resetToken;
}

public void setResetToken(String resetToken) {
    this.resetToken = resetToken;
}

public LocalDateTime getResetTokenExpiry() {
    return resetTokenExpiry;
}

public void setResetTokenExpiry(LocalDateTime resetTokenExpiry) {
    this.resetTokenExpiry = resetTokenExpiry;
}

@Override
public String toString() {
	return "JobSeeker [jobSeekerId=" + jobSeekerId + ", firstName=" + firstName + ", lastName=" + lastName
			+ ", emailAddress=" + emailAddress + ", password=" + password + ", confirmPassword=" + confirmPassword
			+ ", phoneNumber=" + phoneNumber + ", location=" + location + ", resumePath=" + resumePath
			+ ", profilePicturePath=" + profilePicturePath + ", status=" + status + ", skills=" + skills
			+ ", education=" + education + ", projects=" + projects + "]";
}
  
  
}
