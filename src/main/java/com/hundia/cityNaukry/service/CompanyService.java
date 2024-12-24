package com.hundia.cityNaukry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.dao.CompanyRepository;
import com.hundia.cityNaukry.pojo.Company;

@Service
public class CompanyService implements ICompanyService{

	@Autowired
	private CompanyRepository companyRepo;

	@Override
	  public boolean saveCompany(Company company) {
        try {
        	companyRepo.save(company); // Save the company object to the database
            return true; // Return true if saving is successful
        } catch (Exception e) {
            e.printStackTrace(); // Log the error for debugging
            return false; // Return false if an error occurs
        }
    }


	
	@Override
	public Company findByCompanyEmail(String companyEmail) {
		 return (( companyRepo.findByCompanyEmail(companyEmail))
		            .orElse(null)); 
	}



	@Override
	public Company getCompany(long companyId) {
        return companyRepo.findById(companyId).orElse(null);
    }



	@Override
	public Company findById(Long companyId) {
		return companyRepo.findById(companyId).orElse(null); 
	}



	 @Override
	    public boolean updateCompany(Company existingCompany) {
	        try {
	            // Check if the company exists in the database
	            Company company = companyRepo.findById(existingCompany.getCompanyId()).orElse(null);
	            if (company == null) {
	                return false; // Return false if the company does not exist
	            }

	            // Update the company details
	            company.setCompanyName(existingCompany.getCompanyName());
	            company.setIndustryType(existingCompany.getIndustryType());
	            company.setCompanySize(existingCompany.getCompanySize());
	            company.setCompanyWebsite(existingCompany.getCompanyWebsite());
	            company.setCompanyDescription(existingCompany.getCompanyDescription());
	            company.setCompanyEmail(existingCompany.getCompanyEmail());
	            company.setPassword(existingCompany.getPassword());
	            company.setContactPerson(existingCompany.getContactPerson());
	            company.setContactPersonDesignation(existingCompany.getContactPersonDesignation());
	            company.setContactEmail(existingCompany.getContactEmail());
	            company.setContactPhoneNumber(existingCompany.getContactPhoneNumber());
	            company.setCompanyAddress(existingCompany.getCompanyAddress());
	            company.setCompanyLogoPath(existingCompany.getCompanyLogoPath());

	            // Save the updated company details
	            companyRepo.save(company);
	            return true;
	        } catch (Exception e) {
	            e.printStackTrace(); // Log the error for debugging
	            return false; // Return false if an error occurs
	        }
	    }



	 @Override
	 public boolean deleteCompany(Long companyId) {
	     try {
	         // Check if the company exists in the database
	         if (companyRepo.existsById(companyId)) {
	             // Delete the company by its ID
	        	 companyRepo.deleteById(companyId);
	             return true;  // Return true if deletion is successful
	         } else {
	             return false; // Return false if the company does not exist
	         }
	     } catch (Exception e) {
	         // Log the error
	         e.printStackTrace();
	         return false; // Return false in case of any errors
	     }
	 }



	@Override
	public List<Company> findAllCompanies() {
		        return companyRepo.findAll();
	}

	 
	
}
