//package com.hundia.cityNaukry.pojo;
//
//
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.ManyToOne;
//
//@Entity
//public class JobPost {
//	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long jobPostId;
//	private String title;
//    private String description;
//    private String responsibilities;
//    private String companyName;
//    private String location;
//    private String salaryRange;
//    private String qualifications;
//    private int experienceRequired;
//    private String employmentType;
//    
//   
//    public JobPost() {
//		// TODO Auto-generated constructor stub
//	}
//
//
//	public JobPost(String title, String description, String responsibilities, String companyName, String location,
//			String salaryRange, String qualifications, int experienceRequired, String employmentType) {
//		super();
//		this.title = title;
//		this.description = description;
//		this.responsibilities = responsibilities;
//		this.companyName = companyName;
//		this.location = location;
//		this.salaryRange = salaryRange;
//		this.qualifications = qualifications;
//		this.experienceRequired = experienceRequired;
//		this.employmentType = employmentType;
//	}
//
//
//	public String getTitle() {
//		return title;
//	}
//
//
//	public void setTitle(String title) {
//		this.title = title;
//	}
//
//
//	public String getDescription() {
//		return description;
//	}
//
//
//	public void setDescription(String description) {
//		this.description = description;
//	}
//
//
//	public String getResponsibilities() {
//		return responsibilities;
//	}
//
//
//	public void setResponsibilities(String responsibilities) {
//		this.responsibilities = responsibilities;
//	}
//
//
//	public String getCompanyName() {
//		return companyName;
//	}
//
//
//	public void setCompanyName(String companyName) {
//		this.companyName = companyName;
//	}
//
//
//	public String getLocation() {
//		return location;
//	}
//
//
//	public void setLocation(String location) {
//		this.location = location;
//	}
//
//
//	public String getSalaryRange() {
//		return salaryRange;
//	}
//
//
//	public void setSalaryRange(String salaryRange) {
//		this.salaryRange = salaryRange;
//	}
//
//
//	public String getQualifications() {
//		return qualifications;
//	}
//
//
//	public void setQualifications(String qualifications) {
//		this.qualifications = qualifications;
//	}
//
//
//	public int getExperienceRequired() {
//		return experienceRequired;
//	}
//
//
//	public void setExperienceRequired(int experienceRequired) {
//		this.experienceRequired = experienceRequired;
//	}
//
//
//	public String getEmploymentType() {
//		return employmentType;
//	}
//
//
//	public void setEmploymentType(String employmentType) {
//		this.employmentType = employmentType;
//	}
//
//
//	@Override
//	public String toString() {
//		return "JobPost [title=" + title + ", description=" + description + ", responsibilities=" + responsibilities
//				+ ", companyName=" + companyName + ", location=" + location + ", salaryRange=" + salaryRange
//				+ ", qualifications=" + qualifications + ", experienceRequired=" + experienceRequired
//				+ ", employmentType=" + employmentType + "]";
//	}
//    
//     
//
//}


package com.hundia.cityNaukry.pojo;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class JobPost {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
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
    
    @ManyToOne
    @JoinColumn(name = "company_id", referencedColumnName = "companyId")
    @JsonBackReference
    private Company company;
    
   public JobPost() {
	// TODO Auto-generated constructor stub
}

public JobPost(long jobPostId, String title, String description, String responsibilities, String companyName,
		String location, String salaryRange, String qualifications, int experienceRequired, String employmentType,
		Company company) {
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
	this.company = company;
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

public Company getCompany() {
	return company;
}

public void setCompany(Company company) {
	this.company = company;
}

@Override
public String toString() {
	return "JobPost [jobPostId=" + jobPostId + ", title=" + title + ", description=" + description
			+ ", responsibilities=" + responsibilities + ", companyName=" + companyName + ", location=" + location
			+ ", salaryRange=" + salaryRange + ", qualifications=" + qualifications + ", experienceRequired="
			+ experienceRequired + ", employmentType=" + employmentType + ", company=" + company + "]";
}
}





