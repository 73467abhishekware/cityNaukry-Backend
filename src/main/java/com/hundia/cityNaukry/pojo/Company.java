//package com.hundia.cityNaukry.pojo;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.OneToMany;
//
//@Entity
//public class Company {
//	
//	    @Id
//	    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    	private long CompanyId;
//	    @Column(unique = true)
//	    private String companyName;
//	    private String industryType;
//	    private String companySize;
//	    private String companyWebsite;
//	    private String companyDescription;
//
//	    // Account Information
//	    private String contactPerson;
//	    private String contactPersonDesignation;
//	    private String contactEmail;
//	    private String contactPhoneNumber;
//	    private String companyAddress;
//	    
//	    private String companyLogoPath; 
//	    
//	    public Company() {
//			// TODO Auto-generated constructor stub
//		}
//
//		public Company(String companyName, String industryType, String companySize, String companyWebsite,
//				String companyDescription, String contactPerson, String contactPersonDesignation, String contactEmail,
//				String contactPhoneNumber, String companyAddress, String companyLogoPath) {
//			super();
//			this.companyName = companyName;
//			this.industryType = industryType;
//			this.companySize = companySize;
//			this.companyWebsite = companyWebsite;
//			this.companyDescription = companyDescription;
//			this.contactPerson = contactPerson;
//			this.contactPersonDesignation = contactPersonDesignation;
//			this.contactEmail = contactEmail;
//			this.contactPhoneNumber = contactPhoneNumber;
//			this.companyAddress = companyAddress;
//			this.companyLogoPath = companyLogoPath;
//		}
//
//
//
//
//		public long getCompanyId() {
//			return CompanyId;
//		}
//
//		public void setCompanyId(long companyId) {
//			CompanyId = companyId;
//		}
//
//		public String getCompanyName() {
//			return companyName;
//		}
//
//		public void setCompanyName(String companyName) {
//			this.companyName = companyName;
//		}
//
//		public String getIndustryType() {
//			return industryType;
//		}
//
//		public void setIndustryType(String industryType) {
//			this.industryType = industryType;
//		}
//
//		public String getCompanySize() {
//			return companySize;
//		}
//
//		public void setCompanySize(String companySize) {
//			this.companySize = companySize;
//		}
//
//		public String getCompanyWebsite() {
//			return companyWebsite;
//		}
//
//		public void setCompanyWebsite(String companyWebsite) {
//			this.companyWebsite = companyWebsite;
//		}
//
//		public String getCompanyDescription() {
//			return companyDescription;
//		}
//
//		public void setCompanyDescription(String companyDescription) {
//			this.companyDescription = companyDescription;
//		}
//
//		public String getContactPerson() {
//			return contactPerson;
//		}
//
//		public void setContactPerson(String contactPerson) {
//			this.contactPerson = contactPerson;
//		}
//
//		public String getContactPersonDesignation() {
//			return contactPersonDesignation;
//		}
//
//		public void setContactPersonDesignation(String contactPersonDesignation) {
//			this.contactPersonDesignation = contactPersonDesignation;
//		}
//
//		public String getContactEmail() {
//			return contactEmail;
//		}
//
//		public void setContactEmail(String contactEmail) {
//			this.contactEmail = contactEmail;
//		}
//
//		public String getContactPhoneNumber() {
//			return contactPhoneNumber;
//		}
//
//		public void setContactPhoneNumber(String contactPhoneNumber) {
//			this.contactPhoneNumber = contactPhoneNumber;
//		}
//
//		public String getCompanyAddress() {
//			return companyAddress;
//		}
//
//		public void setCompanyAddress(String companyAddress) {
//			this.companyAddress = companyAddress;
//		}
//		
//		  public String getCompanyLogoPath() {
//		        return companyLogoPath;
//		    }
//
//		    public void setCompanyLogoPath(String companyLogoPath) {
//		        this.companyLogoPath = companyLogoPath;
//		    }
//
//		@Override
//		public String toString() {
//			return "Company [CompanyId=" + CompanyId + ", companyName=" + companyName + ", industryType=" + industryType
//					+ ", companySize=" + companySize + ", companyWebsite=" + companyWebsite + ", companyDescription="
//					+ companyDescription + ", contactPerson=" + contactPerson + ", contactPersonDesignation="
//					+ contactPersonDesignation + ", contactEmail=" + contactEmail + ", contactPhoneNumber="
//					+ contactPhoneNumber + ", companyAddress=" + companyAddress + ", companyLogoPath=" + companyLogoPath + "]";
//		}
//
//
//}


package com.hundia.cityNaukry.pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Company {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
    	private long CompanyId;
	    @Column(unique = true)
	    private String companyName;
	    private String industryType;
	    private String companySize;
	    private String companyWebsite;
	    private String companyDescription;
       
	    // Account Information
	    private String companyEmail;
	    private String password;
	    private String contactPerson;
	    private String contactPersonDesignation;
	    private String contactEmail;
	    private String contactPhoneNumber;
	    private String companyAddress;
	    
	    private String companyLogoPath; 
	    
	    private String status = "company";
	    
	    @OneToMany(mappedBy = "company")
	    @JsonManagedReference
	    private List<JobPost> jobPosts;  
	    
	  
	    
	 public Company() {
		// TODO Auto-generated constructor stub
	}



	public Company(String companyName, String industryType, String companySize, String companyWebsite,
			String companyDescription, String companyEmail, String password, String contactPerson,
			String contactPersonDesignation, String contactEmail, String contactPhoneNumber, String companyAddress,
			String companyLogoPath, String status, List<JobPost> jobPosts) {
		super();
		this.companyName = companyName;
		this.industryType = industryType;
		this.companySize = companySize;
		this.companyWebsite = companyWebsite;
		this.companyDescription = companyDescription;
		this.companyEmail = companyEmail;
		this.password = password;
		this.contactPerson = contactPerson;
		this.contactPersonDesignation = contactPersonDesignation;
		this.contactEmail = contactEmail;
		this.contactPhoneNumber = contactPhoneNumber;
		this.companyAddress = companyAddress;
		this.companyLogoPath = companyLogoPath;
		this.status = "company";
		this.jobPosts = jobPosts;
	}



	public long getCompanyId() {
		return CompanyId;
	}



	public void setCompanyId(long companyId) {
		CompanyId = companyId;
	}



	public String getCompanyName() {
		return companyName;
	}



	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}



	public String getIndustryType() {
		return industryType;
	}



	public void setIndustryType(String industryType) {
		this.industryType = industryType;
	}



	public String getCompanySize() {
		return companySize;
	}



	public void setCompanySize(String companySize) {
		this.companySize = companySize;
	}



	public String getCompanyWebsite() {
		return companyWebsite;
	}



	public void setCompanyWebsite(String companyWebsite) {
		this.companyWebsite = companyWebsite;
	}



	public String getCompanyDescription() {
		return companyDescription;
	}



	public void setCompanyDescription(String companyDescription) {
		this.companyDescription = companyDescription;
	}



	public String getCompanyEmail() {
		return companyEmail;
	}



	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getContactPerson() {
		return contactPerson;
	}



	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}



	public String getContactPersonDesignation() {
		return contactPersonDesignation;
	}



	public void setContactPersonDesignation(String contactPersonDesignation) {
		this.contactPersonDesignation = contactPersonDesignation;
	}



	public String getContactEmail() {
		return contactEmail;
	}



	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}



	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}



	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}



	public String getCompanyAddress() {
		return companyAddress;
	}



	public void setCompanyAddress(String companyAddress) {
		this.companyAddress = companyAddress;
	}



	public String getCompanyLogoPath() {
		return companyLogoPath;
	}



	public void setCompanyLogoPath(String companyLogoPath) {
		this.companyLogoPath = companyLogoPath;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}



	public List<JobPost> getJobPosts() {
		return jobPosts;
	}



	public void setJobPosts(List<JobPost> jobPosts) {
		this.jobPosts = jobPosts;
	}



	@Override
	public String toString() {
		return "Company [CompanyId=" + CompanyId + ", companyName=" + companyName + ", industryType=" + industryType
				+ ", companySize=" + companySize + ", companyWebsite=" + companyWebsite + ", companyDescription="
				+ companyDescription + ", companyEmail=" + companyEmail + ", password=" + password + ", contactPerson="
				+ contactPerson + ", contactPersonDesignation=" + contactPersonDesignation + ", contactEmail="
				+ contactEmail + ", contactPhoneNumber=" + contactPhoneNumber + ", companyAddress=" + companyAddress
				+ ", companyLogoPath=" + companyLogoPath + ", status=" + status + ", jobPosts=" + jobPosts + "]";
	}
}

