package com.hundia.cityNaukry.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.pojo.Company;

@Service
public interface ICompanyService {

	boolean saveCompany(Company company);

	Company findByCompanyEmail(String companyEmail);

	Company getCompany(long companyId);

	Company findById(Long companyId);

	boolean updateCompany(Company existingCompany);

	boolean deleteCompany(Long companyId);

	List<Company> findAllCompanies();

	
}
