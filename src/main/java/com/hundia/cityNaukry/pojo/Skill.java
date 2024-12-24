package com.hundia.cityNaukry.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Skill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skillName;
    private String skillLevel;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "job_seeker_id", nullable = false)
    private JobSeeker jobSeeker;
    
    public Skill() {
		// TODO Auto-generated constructor stub
	}

	public Skill(Long id, String skillName, String skillLevel, JobSeeker jobSeeker) {
		super();
		this.id = id;
		this.skillName = skillName;
		this.skillLevel = skillLevel;
		this.jobSeeker = jobSeeker;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSkillName() {
		return skillName;
	}

	public void setSkillName(String skillName) {
		this.skillName = skillName;
	}

	public String getSkillLevel() {
		return skillLevel;
	}

	public void setSkillLevel(String skillLevel) {
		this.skillLevel = skillLevel;
	}

	public JobSeeker getJobSeeker() {
		return jobSeeker;
	}

	public void setJobSeeker(JobSeeker jobSeeker) {
		this.jobSeeker = jobSeeker;
	}

	@Override
	public String toString() {
		return "Skill [id=" + id + ", skillName=" + skillName + ", skillLevel=" + skillLevel + ", jobSeeker="
				+ jobSeeker + "]";
	}
    
    

 
}