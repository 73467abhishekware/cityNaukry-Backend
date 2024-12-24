package com.hundia.cityNaukry.dto;


import java.util.Date;

public class InterviewDTO {
    private Long jobSeekerId;
    private Long jobPostId;
    private Date interviewDate;
    private String interviewLocation;
    private String interviewTime;
    private String interviewMode; // Interview mode (In-person, Online)
    private String interviewLink; // Online interview link (optional)
    private String interviewer; // Interviewer's name
    private String interviewMaterial; // Materials for the interview
    private int interviewDuration; // Duration in minutes

    // Getters and Setters

    public Long getJobSeekerId() {
        return jobSeekerId;
    }

    public void setJobSeekerId(Long jobSeekerId) {
        this.jobSeekerId = jobSeekerId;
    }

    public Long getJobPostId() {
        return jobPostId;
    }

    public void setJobPostId(Long jobPostId) {
        this.jobPostId = jobPostId;
    }

    public Date getInterviewDate() {
        return interviewDate;
    }

    public void setInterviewDate(Date interviewDate) {
        this.interviewDate = interviewDate;
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
}
