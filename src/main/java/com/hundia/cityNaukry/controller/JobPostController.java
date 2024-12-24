
package com.hundia.cityNaukry.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hundia.cityNaukry.dao.CompanyRepository;
import com.hundia.cityNaukry.dto.ErrorResponse;
import com.hundia.cityNaukry.pojo.JobPost;
import com.hundia.cityNaukry.pojo.Company;
import com.hundia.cityNaukry.service.ICompanyService;
import com.hundia.cityNaukry.service.IJobPostService;


@RestController
@RequestMapping("/jobPost")
@CrossOrigin("*")
public class JobPostController {

    @Autowired
    private IJobPostService jobPostService;

    @Autowired
    private ICompanyService companyService;
    
    @Autowired
    private CompanyRepository companyRepository; // This will help us save the company if needed

    
    @PostMapping("/addPost")
    public ResponseEntity<?> registerJobPost(@RequestBody JobPost jobPost, @RequestParam long companyId) {
        System.out.println("Received Company ID: " + companyId);
        System.out.println("Received Job Post: " + jobPost);
        
        try {
            // Fetch the company using the passed companyId
            Company company = companyService.getCompany(companyId);
            
            // Ensure the company is linked to the job post
            if (company != null) {
                jobPost.setCompany(company);  // Link the saved company to the job post
            } else {
                // Return an error response if the company is not found
                return new ResponseEntity<>(new ErrorResponse("Company not found", "No company found with the provided ID", null), HttpStatus.BAD_REQUEST);
            }

            // Save the job post with the company properly linked
            JobPost dbPost = jobPostService.addPost(jobPost);
            return new ResponseEntity<>(dbPost, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Post registration failed", e.getMessage(), null), HttpStatus.BAD_REQUEST);
        }
    }


    
    

//    //http://localhost:8086/jobPost/getAllPosts
//    @GetMapping("/getAllPosts")
//    public ResponseEntity<List<JobPost>> getAllJobPosts() {
//        try {
//            List<JobPost> jobPosts = jobPostService.getAllPosts(); // Assuming the service has a method to fetch all posts
//            return new ResponseEntity<>(jobPosts, HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//    
    
    
    @GetMapping("/getAllPosts")
    public ResponseEntity<List<Map<String, Object>>> getAllJobPosts() {
        try {
            List<JobPost> jobPosts = jobPostService.getAllPosts();
            List<Map<String, Object>> response = jobPosts.stream().map(jobPost -> {
                Map<String, Object> jobData = new HashMap<>();
                jobData.put("jobPostId", jobPost.getJobPostId());
                jobData.put("title", jobPost.getTitle());
                jobData.put("description", jobPost.getDescription());
                jobData.put("responsibilities", jobPost.getResponsibilities());
                jobData.put("companyName", jobPost.getCompanyName());
                jobData.put("location", jobPost.getLocation());
                jobData.put("salaryRange", jobPost.getSalaryRange());
                jobData.put("qualifications", jobPost.getQualifications());
                jobData.put("experienceRequired", jobPost.getExperienceRequired());
                jobData.put("employmentType", jobPost.getEmploymentType());

                if (jobPost.getCompany() != null) {
                    Map<String, Object> companyData = new HashMap<>();
                    companyData.put("companyId", jobPost.getCompany().getCompanyId());
                    companyData.put("name", jobPost.getCompany().getCompanyName());
                    companyData.put("logoPath", jobPost.getCompany().getCompanyLogoPath());
                    jobData.put("company", companyData);
                }

                return jobData;
            }).toList();

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    
    //http://localhost:8086/jobPost/getPostsByCompany
    @GetMapping("/getPostsByCompany")
    public ResponseEntity<?> getPostsByCompany(@RequestParam long companyId) {
        try {
            // Fetch the company by ID
            Company company = companyService.getCompany(companyId);

            if (company == null) {
                // Return an error if the company is not found
                return new ResponseEntity<>(new ErrorResponse("Company not found", "No company found with the provided ID", null), HttpStatus.BAD_REQUEST);
            }

            // Retrieve job posts for the given company
            List<JobPost> jobPosts = jobPostService.getPostsByCompany(company);

            if (jobPosts.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse("No Job Posts Found", "No job posts available for the provided company", null), HttpStatus.NOT_FOUND);
            }

            // Return the job posts
            return new ResponseEntity<>(jobPosts, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Failed to retrieve job posts", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

//    //http://localhost:8086/jobPost/getPostById/{jobPostId}
//    @GetMapping("/getPostById/{jobPostId}")
//    public ResponseEntity<?> getJobPostById(@PathVariable long jobPostId) {
//        try {
//            // Fetch the job post by jobPostId
//            JobPost jobPost = jobPostService.getJobPostById(jobPostId);
//            
//            if (jobPost == null) {
//                // If no job post is found, return a 404 Not Found response
//                return new ResponseEntity<>(new ErrorResponse("Job Post Not Found", 
//                        "No job post found with the provided ID", null), HttpStatus.NOT_FOUND);
//            }
//
//            // Return the job post if found
//            return new ResponseEntity<>(jobPost, HttpStatus.OK);
//        } catch (Exception e) {
//            // Handle unexpected errors
//            return new ResponseEntity<>(new ErrorResponse("Failed to retrieve job post", e.getMessage(), null), 
//                    HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
    
    @GetMapping("/getPostById/{jobPostId}")
    public ResponseEntity<?> getJobPostById(@PathVariable long jobPostId) {
        try {
            JobPost jobPost = jobPostService.getJobPostById(jobPostId);

            if (jobPost == null) {
                return new ResponseEntity<>(new ErrorResponse("Job Post Not Found",
                        "No job post found with the provided ID", null), HttpStatus.NOT_FOUND);
            }

            // Create a custom response including company information
            Map<String, Object> response = new HashMap();
            response.put("jobPostId", jobPost.getJobPostId());
            response.put("title", jobPost.getTitle());
            response.put("description", jobPost.getDescription());
            response.put("responsibilities", jobPost.getResponsibilities());
            response.put("companyName", jobPost.getCompanyName());
            response.put("location", jobPost.getLocation());
            response.put("salaryRange", jobPost.getSalaryRange());
            response.put("qualifications", jobPost.getQualifications());
            response.put("experienceRequired", jobPost.getExperienceRequired());
            response.put("employmentType", jobPost.getEmploymentType());

            // Include company information
            Company company = jobPost.getCompany();
            if (company != null) {
                Map<String, Object> companyInfo = new HashMap();
                companyInfo.put("name", company.getCompanyName());
                companyInfo.put("logoPath", company.getCompanyLogoPath());
                companyInfo.put("address", company.getCompanyAddress());
                companyInfo.put("website", company.getCompanyWebsite());
                companyInfo.put("description", company.getCompanyDescription());
                response.put("companyInformation", companyInfo);
            }

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new ErrorResponse("Failed to retrieve job post", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    
    
    
    
    
  //http://localhost:8086/jobPost//deletePost/{jobPostId}
    @DeleteMapping("/deletePost/{jobPostId}")
    public ResponseEntity<?> deleteJobPost(@PathVariable long jobPostId) {
        try {
            // Fetch the job post by jobPostId
            JobPost jobPost = jobPostService.getJobPostById(jobPostId);

            if (jobPost == null) {
                // If no job post is found, return a 404 Not Found response
                return new ResponseEntity<>(new ErrorResponse("Job Post Not Found", 
                        "No job post found with the provided ID", null), HttpStatus.NOT_FOUND);
            }

            // Proceed to delete the job post
            jobPostService.deletePost(jobPostId);

            // Return a success response
            return new ResponseEntity<>("Job post deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            // Handle any errors that occur during deletion
            return new ResponseEntity<>(new ErrorResponse("Failed to delete job post", e.getMessage(), null), 
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

 // http://localhost:8086/jobPost/updatePost/{jobPostId}
    @PutMapping("/updatePost/{jobPostId}")
    public ResponseEntity<?> updateJobPost(@PathVariable long jobPostId, @RequestBody JobPost jobPost) {
        try {
            // Fetch the existing job post using the jobPostId
            JobPost existingJobPost = jobPostService.getJobPostById(jobPostId);

            if (existingJobPost == null) {
                // If no job post is found, return a 404 Not Found response
                return new ResponseEntity<>(new ErrorResponse("Job Post Not Found", 
                        "No job post found with the provided ID", null), HttpStatus.NOT_FOUND);
            }

            // Update the job post fields with the new details
            existingJobPost.setTitle(jobPost.getTitle());
            existingJobPost.setDescription(jobPost.getDescription());
            existingJobPost.setResponsibilities(jobPost.getResponsibilities());
            existingJobPost.setLocation(jobPost.getLocation());
            existingJobPost.setSalaryRange(jobPost.getSalaryRange());
            existingJobPost.setQualifications(jobPost.getQualifications());
            existingJobPost.setExperienceRequired(jobPost.getExperienceRequired());
            existingJobPost.setEmploymentType(jobPost.getEmploymentType());

            // If a company is provided in the request, update the company as well
            if (jobPost.getCompany() != null) {
                existingJobPost.setCompany(jobPost.getCompany());
            }

            // Save the updated job post
            JobPost updatedJobPost = jobPostService.updatePost(existingJobPost);

            // Return the updated job post
            return new ResponseEntity<>(updatedJobPost, HttpStatus.OK);

        } catch (Exception e) {
            // Handle any errors during the update process
            return new ResponseEntity<>(new ErrorResponse("Failed to update job post", e.getMessage(), null),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
