package com.hundia.cityNaukry.pojo;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id")
    private Long interviewId;

    @ManyToOne 
    private JobSeeker jobSeeker;

    @ManyToOne
    private JobPost jobPost;
 
                
    private Date interviewDate;
    private String interviewLocation;
    private String interviewTime;

    private String interviewMode; // In-person, Online, etc.
    private String interviewLink; // Link for online interviews
    private String interviewer;   // Name of the interviewer
    private String interviewMaterial; // Materials needed for the interview
    private int interviewDuration;  // Duration in minutes
    
    
    public Interview() {
		// TODO Auto-generated constructor stub
	}


	public Interview(Long interviewId, JobSeeker jobSeeker, JobPost jobPost, Date interviewDate, String interviewLocation,
			String interviewTime, String interviewMode, String interviewLink, String interviewer,
			String interviewMaterial, int interviewDuration) {
		super();
		this.interviewId = interviewId;
		this.jobSeeker = jobSeeker;
		this.jobPost = jobPost;
		this.interviewDate = interviewDate;
		this.interviewLocation = interviewLocation;
		this.interviewTime = interviewTime;
		this.interviewMode = interviewMode;
		this.interviewLink = interviewLink;
		this.interviewer = interviewer;
		this.interviewMaterial = interviewMaterial;
		this.interviewDuration = interviewDuration;
	}


	public Long getId() {
		return interviewId;
	}


	public void setId(Long interviewId) {
		this.interviewId = interviewId;
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


	public Date getInterviewDate() {
		return interviewDate;
	}


	public void setInterviewDate(java.util.Date date) {
	    this.interviewDate = new Date(date.getTime());
	}



	public String getInterviewLocation() {
		return interviewLocation;
	}


	public void setInterviewLocation(String interviewLocation) {
		this.interviewLocation = interviewLocation;
	}


	public String getInterviewTime() {
		return interviewTime;
	}


	public void setInterviewTime(String interviewTime) {
		this.interviewTime = interviewTime;
	}


	public String getInterviewMode() {
		return interviewMode;
	}


	public void setInterviewMode(String interviewMode) {
		this.interviewMode = interviewMode;
	}


	public String getInterviewLink() {
		return interviewLink;
	}


	public void setInterviewLink(String interviewLink) {
		this.interviewLink = interviewLink;
	}


	public String getInterviewer() {
		return interviewer;
	}


	public void setInterviewer(String interviewer) {
		this.interviewer = interviewer;
	}


	public String getInterviewMaterial() {
		return interviewMaterial;
	}


	public void setInterviewMaterial(String interviewMaterial) {
		this.interviewMaterial = interviewMaterial;
	}


	public int getInterviewDuration() {
		return interviewDuration;
	}


	public void setInterviewDuration(int interviewDuration) {
		this.interviewDuration = interviewDuration;
	}


	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", jobSeeker=" + jobSeeker + ", jobPost=" + jobPost + ", interviewDate="
				+ interviewDate + ", interviewLocation=" + interviewLocation + ", interviewTime=" + interviewTime
				+ ", interviewMode=" + interviewMode + ", interviewLink=" + interviewLink + ", interviewer="
				+ interviewer + ", interviewMaterial=" + interviewMaterial + ", interviewDuration=" + interviewDuration
				+ "]";
	}
    
    

}

//import java.sql.Date;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//
//@Entity
//@Table(name = "interview")
//public class Interview {
//
//    @Id 
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "interview_id")
//    private Long interviewId;
//
//    @ManyToOne
//    @JoinColumn(name = "job_post_id", referencedColumnName = "job_post_id")
//    private JobPost jobPost;
//
//    @ManyToOne
//    @JoinColumn(name = "job_seeker_job_seeker_id", referencedColumnName = "job_seeker_id")
//    private JobSeeker jobSeeker;
//
//    @Column(name = "interview_date")
//    private Date interviewDate;  // Keep java.sql.Date
//
//    @Column(name = "interview_location")
//    private String interviewLocation;
//
//    @Column(name = "interview_time")
//    private String interviewTime;
//
//    @Column(name = "interview_mode")
//    private String interviewMode;
//
//    @Column(name = "interview_link")
//    private String interviewLink;
//
//    @Column(name = "interview_material")
//    private String interviewMaterial;
//
//    @Column(name = "interview_duration")
//    private Integer interviewDuration;
//
//    @Column(name = "interviewer")
//    private String interviewer;
//
//    public Interview() {
//        // Default constructor
//    }
//
//    public Interview(Long interviewId, JobPost jobPost, JobSeeker jobSeeker, Date interviewDate,
//                     String interviewLocation, String interviewTime, String interviewMode, String interviewLink,
//                     String interviewMaterial, Integer interviewDuration, String interviewer) {
//        this.interviewId = interviewId;
//        this.jobPost = jobPost;
//        this.jobSeeker = jobSeeker;
//        this.interviewDate = interviewDate;
//        this.interviewLocation = interviewLocation;
//        this.interviewTime = interviewTime;
//        this.interviewMode = interviewMode;
//        this.interviewLink = interviewLink;
//        this.interviewMaterial = interviewMaterial;
//        this.interviewDuration = interviewDuration;
//        this.interviewer = interviewer;
//    }
//
//    // Getters and setters
//
//    public Long getInterviewId() {
//        return interviewId;
//    }
//
//    public void setInterviewId(Long interviewId) {
//        this.interviewId = interviewId;
//    }
//
//    public JobPost getJobPost() {
//        return jobPost;
//    }
//
//    public void setJobPost(JobPost jobPost) {
//        this.jobPost = jobPost;
//    }
//
//    public JobSeeker getJobSeeker() {
//        return jobSeeker;
//    }
//
//    public void setJobSeeker(JobSeeker jobSeeker) {
//        this.jobSeeker = jobSeeker;
//    }
//
//    public Date getInterviewDate() {
//        return interviewDate;
//    }
//
//    public void setInterviewDate(java.util.Date date) {
//        this.interviewDate = new Date(date.getTime());  // Convert java.util.Date to java.sql.Date
//    }
//
//    public String getInterviewLocation() {
//        return interviewLocation;
//    }
//
//    public void setInterviewLocation(String interviewLocation) {
//        this.interviewLocation = interviewLocation;
//    }
//
//    public String getInterviewTime() {
//        return interviewTime;
//    }
//
//    public void setInterviewTime(String interviewTime) {
//        this.interviewTime = interviewTime;
//    }
//
//    public String getInterviewMode() {
//        return interviewMode;
//    }
//
//    public void setInterviewMode(String interviewMode) {
//        this.interviewMode = interviewMode;
//    }
//
//    public String getInterviewLink() {
//        return interviewLink;
//    }
//
//    public void setInterviewLink(String interviewLink) {
//        this.interviewLink = interviewLink;
//    }
//
//    public String getInterviewMaterial() {
//        return interviewMaterial;
//    }
//
//    public void setInterviewMaterial(String interviewMaterial) {
//        this.interviewMaterial = interviewMaterial;
//    }
//
//    public Integer getInterviewDuration() {
//        return interviewDuration;
//    }
//
//    public void setInterviewDuration(Integer interviewDuration) {
//        this.interviewDuration = interviewDuration;
//    }
//
//    public String getInterviewer() {
//        return interviewer;
//    }
//
//    public void setInterviewer(String interviewer) {
//        this.interviewer = interviewer;
//    }
//
//    @Override
//    public String toString() {
//        return "Interview [interviewId=" + interviewId + ", jobPost=" + jobPost + ", jobSeeker=" + jobSeeker
//                + ", interviewDate=" + interviewDate + ", interviewLocation=" + interviewLocation + ", interviewTime="
//                + interviewTime + ", interviewMode=" + interviewMode + ", interviewLink=" + interviewLink
//                + ", interviewMaterial=" + interviewMaterial + ", interviewDuration=" + interviewDuration
//                + ", interviewer=" + interviewer + "]";
//    }
//}
//
