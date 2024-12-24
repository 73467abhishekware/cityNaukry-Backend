package com.hundia.cityNaukry.controller;

import com.hundia.cityNaukry.pojo.Company;
import com.hundia.cityNaukry.service.ICompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@RestController
@RequestMapping("company")
@CrossOrigin("*")
public class CompanyController {

    @Autowired
    private ICompanyService companyService;

    // File upload directory path from application.properties
    private static final String UPLOAD_DIR = "C:/JobPortalBackend/demoJobportal/uploads/company_logos/";

    
//    //http://localhost:8086/company/register
//    @PostMapping("/register")
//    public ResponseEntity<String> registerCompany(
//            @RequestParam String companyName,
//            @RequestParam String industryType,
//            @RequestParam String companySize,
//            @RequestParam String companyWebsite,
//            @RequestParam String companyDescription,
//            @RequestParam String companyEmail,
//            @RequestParam String password,
//            @RequestParam String contactPerson,
//            @RequestParam String contactPersonDesignation,
//            @RequestParam String contactEmail,
//            @RequestParam String contactPhoneNumber,
//            @RequestParam String companyAddress,
//            @RequestParam(required = false) MultipartFile companyLogo) {
//
//        try {
//            String companyLogoPath = null;
//
//            // Handle the company logo upload
//            if (companyLogo != null && !companyLogo.isEmpty()) {
//                String logoFileName = companyLogo.getOriginalFilename();
//                Path logoUploadPath = Paths.get(UPLOAD_DIR);
//
//                if (!Files.exists(logoUploadPath)) {
//                    Files.createDirectories(logoUploadPath);
//                }
//
//                Path filePath = logoUploadPath.resolve(logoFileName);
//                Files.copy(companyLogo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//                companyLogoPath = filePath.toString();
//            }
//
//            // Create and populate the Company object
//            Company company = new Company();
//            company.setCompanyName(companyName);
//            company.setIndustryType(industryType);
//            company.setCompanySize(companySize);
//            company.setCompanyWebsite(companyWebsite);
//            company.setCompanyDescription(companyDescription);
//            company.setCompanyEmail(companyEmail);
//            company.setPassword(password);
//            company.setContactPerson(contactPerson);
//            company.setContactPersonDesignation(contactPersonDesignation);
//            company.setContactEmail(contactEmail);
//            company.setContactPhoneNumber(contactPhoneNumber);
//            company.setCompanyAddress(companyAddress);
//            company.setCompanyLogoPath(companyLogoPath);
//
//            // Save the company entity
//            boolean isSaved = companyService.saveCompany(company);
//            if (isSaved) {
//                return new ResponseEntity<>("Company registered successfully!", HttpStatus.CREATED);
//            } else {
//                return new ResponseEntity<>("Error registering the company. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//        } catch (IOException e) {
//            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (Exception e) {
//            return new ResponseEntity<>("Error registering the company: " + e.getMessage(), HttpStatus.BAD_REQUEST);
//        }
//    }

    @PostMapping("/register")
    public ResponseEntity<String> registerCompany(
            @RequestParam String companyName,
            @RequestParam String industryType,
            @RequestParam String companySize,
            @RequestParam String companyWebsite,
            @RequestParam String companyDescription,
            @RequestParam String companyEmail,
            @RequestParam String password,
            @RequestParam String contactPerson,
            @RequestParam String contactPersonDesignation,
            @RequestParam String contactEmail,
            @RequestParam String contactPhoneNumber,
            @RequestParam String companyAddress,
            @RequestParam(required = false) MultipartFile companyLogo) {

        try {
            String companyLogoPath = null;

            // Handle the company logo upload
            if (companyLogo != null && !companyLogo.isEmpty()) {
                String uniqueFileName = System.currentTimeMillis() + "_" + companyLogo.getOriginalFilename();
                Path logoUploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(logoUploadPath)) {
                    Files.createDirectories(logoUploadPath);
                }

                Path filePath = logoUploadPath.resolve(uniqueFileName);
                Files.copy(companyLogo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                companyLogoPath = "/uploads/company_logos/" + uniqueFileName; // Save relative path
            }

            // Hash the password
           

            // Create and populate the Company object
            Company company = new Company();
            company.setCompanyName(companyName);
            company.setIndustryType(industryType);
            company.setCompanySize(companySize);
            company.setCompanyWebsite(companyWebsite);
            company.setCompanyDescription(companyDescription);
            company.setCompanyEmail(companyEmail);
            company.setPassword(password);
            company.setContactPerson(contactPerson);
            company.setContactPersonDesignation(contactPersonDesignation);
            company.setContactEmail(contactEmail);
            company.setContactPhoneNumber(contactPhoneNumber);
            company.setCompanyAddress(companyAddress);
            company.setCompanyLogoPath(companyLogoPath);

            // Save the company entity
            boolean isSaved = companyService.saveCompany(company);
            if (isSaved) {
                return new ResponseEntity<>("Company registered successfully!", HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Error registering the company. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering the company: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    
        
    //http://localhost:8086/company/login
    @GetMapping("/login")
    public ResponseEntity<?> loginCompany(@RequestParam String companyEmail, @RequestParam String password) {
        try {
            // Fetch the company details based on the provided email
            Company company = companyService.findByCompanyEmail(companyEmail);

            if (company == null) {
                return new ResponseEntity<>("Company not found. Please register first.", HttpStatus.NOT_FOUND);
            }

            // Validate the password
            if (!password.equals(company.getPassword())) {
                return new ResponseEntity<>("Invalid password. Please try again.", HttpStatus.UNAUTHORIZED);
            }

            // If login is successful, return company details
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (Exception e) {
            // Log the error for debugging and return an appropriate error message
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while processing the request: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
  //http://localhost:8086/company//getCompany/{companyId}
    @GetMapping("/getCompany/{companyId}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long companyId) {
        try {
            // Fetch the company details using companyId
            Company company = companyService.findById(companyId);

            // Check if company exists
            if (company == null) {
                return new ResponseEntity<>("Company not found with the given ID.", HttpStatus.NOT_FOUND);
            }

            // Return the company details
            return new ResponseEntity<>(company, HttpStatus.OK);
        } catch (Exception e) {
            // Log the error and return an appropriate message
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the company: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
 // http://localhost:8086/company/getAllCompanies
    @GetMapping("/getAllCompanies")
    public ResponseEntity<?> getAllCompanies() {
        try {
            // Fetch the list of all companies
            List<Company> companies = companyService.findAllCompanies();

            // Check if there are any companies
            if (companies.isEmpty()) {
                return new ResponseEntity<>("No companies found.", HttpStatus.NOT_FOUND);
            }

            // Return the list of companies
            return new ResponseEntity<>(companies, HttpStatus.OK);
        } catch (Exception e) {
            // Log the error and return an appropriate message
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while fetching the companies: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    
    
    
 // http://localhost:8086/company/update/{companyId}
    @PutMapping("/update/{companyId}")
    public ResponseEntity<String> updateCompany(
            @PathVariable Long companyId,
            @RequestParam(required = false) String companyName,
            @RequestParam(required = false) String industryType,
            @RequestParam(required = false) String companySize,
            @RequestParam(required = false) String companyWebsite,
            @RequestParam(required = false) String companyDescription,
            @RequestParam(required = false) String companyEmail,
            @RequestParam(required = false) String password,
            @RequestParam(required = false) String contactPerson,
            @RequestParam(required = false) String contactPersonDesignation,
            @RequestParam(required = false) String contactEmail,
            @RequestParam(required = false) String contactPhoneNumber,
            @RequestParam(required = false) String companyAddress,
            @RequestParam(required = false) MultipartFile companyLogo) {

        try {
            // Fetch the existing company by ID
            Company existingCompany = companyService.findById(companyId);
            if (existingCompany == null) {
                return new ResponseEntity<>("Company not found with the given ID.", HttpStatus.NOT_FOUND);
            }

            String companyLogoPath = existingCompany.getCompanyLogoPath(); // Preserve existing logo path if not updated

            // Handle the company logo upload if provided
            if (companyLogo != null && !companyLogo.isEmpty()) {
                String uniqueFileName = System.currentTimeMillis() + "_" + companyLogo.getOriginalFilename();
                Path logoUploadPath = Paths.get(UPLOAD_DIR);

                if (!Files.exists(logoUploadPath)) {
                    Files.createDirectories(logoUploadPath);
                }

                Path filePath = logoUploadPath.resolve(uniqueFileName);
                Files.copy(companyLogo.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                companyLogoPath = "/uploads/company_logos/" + uniqueFileName; // Save relative path
            }

            // Update fields only if new values are provided
            if (companyName != null) existingCompany.setCompanyName(companyName);
            if (industryType != null) existingCompany.setIndustryType(industryType);
            if (companySize != null) existingCompany.setCompanySize(companySize);
            if (companyWebsite != null) existingCompany.setCompanyWebsite(companyWebsite);
            if (companyDescription != null) existingCompany.setCompanyDescription(companyDescription);
            if (companyEmail != null) existingCompany.setCompanyEmail(companyEmail);
            if (password != null) existingCompany.setPassword(password);
            if (contactPerson != null) existingCompany.setContactPerson(contactPerson);
            if (contactPersonDesignation != null) existingCompany.setContactPersonDesignation(contactPersonDesignation);
            if (contactEmail != null) existingCompany.setContactEmail(contactEmail);
            if (contactPhoneNumber != null) existingCompany.setContactPhoneNumber(contactPhoneNumber);
            if (companyAddress != null) existingCompany.setCompanyAddress(companyAddress);
            if (companyLogoPath != null) existingCompany.setCompanyLogoPath(companyLogoPath);

            // Save the updated company entity
            boolean isUpdated = companyService.updateCompany(existingCompany);
            if (isUpdated) {
                return new ResponseEntity<>("Company updated successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error updating the company. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (IOException e) {
            return new ResponseEntity<>("File upload failed: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating the company: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    
    
 // http://localhost:8086/company/delete/{companyId}
    @DeleteMapping("/delete/{companyId}")
    public ResponseEntity<String> deleteCompany(@PathVariable Long companyId) {
        try {
            // Fetch the company by ID
            Company company = companyService.findById(companyId);
            
            // Check if the company exists
            if (company == null) {
                return new ResponseEntity<>("Company not found with the given ID.", HttpStatus.NOT_FOUND);
            }

            // Delete the company
            boolean isDeleted = companyService.deleteCompany(companyId);
            if (isDeleted) {
                return new ResponseEntity<>("Company deleted successfully!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Error deleting the company. Please try again.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            // Log the error and return an appropriate message
            e.printStackTrace();
            return new ResponseEntity<>("An error occurred while deleting the company: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
