package com.hundia.cityNaukry.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;




@Entity
public class Education {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String institution;
    private String degree;
    private String startDate;
    private String endDate;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;

    // Getters and Setters
    
    public Education() {
		// TODO Auto-generated constructor stub
	}

	public Education(Long id, String institution, String degree, String startDate, String endDate,
			JobSeeker jobSeeker) {
		super();
		this.id = id;
		this.institution = institution;
		this.degree = degree;
		this.startDate = startDate;
		this.endDate = endDate;
		this.jobSeeker = jobSeeker;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	@Override
	public String toString() {
		return "Education [id=" + id + ", institution=" + institution + ", degree=" + degree + ", startDate="
				+ startDate + ", endDate=" + endDate + ", jobSeeker=" + jobSeeker + "]";
	}
    
    
}