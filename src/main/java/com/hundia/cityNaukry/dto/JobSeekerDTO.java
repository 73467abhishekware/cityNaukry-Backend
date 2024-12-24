package com.hundia.cityNaukry.dto;

import com.hundia.cityNaukry.pojo.JobSeeker;

import com.fasterxml.jackson.annotation.JsonProperty;

public class JobSeekerDTO {
    private long jobSeekerId;
    private String firstName;
    private String lastName;
    private String emailAddress;
    private String phoneNumber;
    private String location;
    private String resumePath;
    private String profilePicturePath;
    private String status;

    public JobSeekerDTO() {
		// TODO Auto-generated constructor stub
	}
    
    public JobSeekerDTO(JobSeeker jobSeeker) {
        this.jobSeekerId = jobSeeker.getJobSeekerId();
        this.firstName = jobSeeker.getFirstName();
        this.lastName = jobSeeker.getLastName();
        this.emailAddress = jobSeeker.getEmailAddress();
        this.phoneNumber = jobSeeker.getPhoneNumber();
        this.location = jobSeeker.getLocation();
        this.resumePath = jobSeeker.getResumePath();
        this.profilePicturePath = jobSeeker.getProfilePicturePath();
        this.status = jobSeeker.getStatus();
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

	@Override
	public String toString() {
		return "JobSeekerDTO [jobSeekerId=" + jobSeekerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailAddress=" + emailAddress + ", phoneNumber=" + phoneNumber + ", location=" + location
				+ ", resumePath=" + resumePath + ", profilePicturePath=" + profilePicturePath + ", status=" + status
				+ "]";
	}

//    @JsonProperty
//    public long getJobSeekerId() {
//        return jobSeekerId;
//    }
//
//    @JsonProperty
//    public String getFirstName() {
//        return firstName;
//    }
//
//    @JsonProperty
//    public String getLastName() {
//        return lastName;
//    }
//
//    @JsonProperty
//    public String getEmailAddress() {
//        return emailAddress;
//    }
//
//    @JsonProperty
//    public String getPhoneNumber() {
//        return phoneNumber;
//    }
//
//    @JsonProperty
//    public String getLocation() {
//        return location;
//    }
//
//    @JsonProperty
//    public String getResumePath() {
//        return resumePath;
//    }
//
//    @JsonProperty
//    public String getProfilePicturePath() {
//        return profilePicturePath;
//    }
//
//    @JsonProperty
//    public String getStatus() {
//        return status;
//    }
    
    
    
}
