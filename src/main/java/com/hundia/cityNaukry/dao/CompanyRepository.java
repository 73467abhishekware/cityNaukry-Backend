package com.hundia.cityNaukry.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hundia.cityNaukry.pojo.Company;

public interface CompanyRepository  extends JpaRepository<Company, Long>{

	Optional<Company> findByCompanyEmail(String companyEmail);

}
