package com.hundia.cityNaukry.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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
import org.springframework.web.multipart.MultipartFile;

import com.hundia.cityNaukry.dto.ApplicationDTO;
import com.hundia.cityNaukry.dto.ErrorResponse;
import com.hundia.cityNaukry.dto.InterviewDTO;
import com.hundia.cityNaukry.dto.JobPostDTO;
import com.hundia.cityNaukry.dto.JobSeekerDTO;
import com.hundia.cityNaukry.dto.SuccessResponse;
import com.hundia.cityNaukry.pojo.Application;
import com.hundia.cityNaukry.pojo.Company;
import com.hundia.cityNaukry.pojo.Education;
import com.hundia.cityNaukry.pojo.Interview;
import com.hundia.cityNaukry.pojo.JobPost;
import com.hundia.cityNaukry.pojo.JobSeeker;
import com.hundia.cityNaukry.pojo.Project;
import com.hundia.cityNaukry.pojo.Skill;
import com.hundia.cityNaukry.service.EmailService;
import com.hundia.cityNaukry.service.IApplicationService;
import com.hundia.cityNaukry.service.IInterviewService;
import com.hundia.cityNaukry.service.IJobPostService;
import com.hundia.cityNaukry.service.IJobSeekerService;


@RestController
@RequestMapping("jobSeeker")
@CrossOrigin("*")
public class JobSeekerController {
	
	@Autowired
	private IJobSeekerService jobseekerService;
	
	 @Autowired
	    private IJobPostService jobPostService;

	    @Autowired
	    private IApplicationService applicationService;
 
	    @Autowired
	    private IInterviewService interviewService;

	    
	    @Autowired
	    private EmailService emailService;
	    
	@PostMapping("/register")
	public ResponseEntity<?> registerJobSeekers(
	        @RequestParam("firstName") String firstName,
	        @RequestParam("lastName") String lastName,
	        @RequestParam("emailAddress") String emailAddress,
	        @RequestParam("password") String password,
	        @RequestParam("confirmPassword") String confirmPassword,
	        @RequestParam("phoneNumber") String phoneNumber,
	        @RequestParam("location") String location,
	        @RequestParam(value = "file", required = true) MultipartFile resumeFile, // make file optional
	        @RequestParam(value = "profilePicture", required = false) MultipartFile profilePictureFile) { // make profile picture optional

	    try {
	        String resumeFileName = null;
	        String profilePictureFileName = null;

	        // Handle resume file if it's present
	        if (resumeFile != null && !resumeFile.isEmpty()) {
	            resumeFileName = resumeFile.getOriginalFilename();
	            String resumeUploadDir = "C:/JobPortalBackend/demoJobportal/uploads/resumes/";
	            Path resumeUploadPath = Paths.get(resumeUploadDir);

	            if (!Files.exists(resumeUploadPath)) {
	                Files.createDirectories(resumeUploadPath);
	            }

	            Path resumeFilePath = resumeUploadPath.resolve(resumeFileName);
	            Files.copy(resumeFile.getInputStream(), resumeFilePath, StandardCopyOption.REPLACE_EXISTING);
	        }

	       // Handle profile picture if it's present
	        if (profilePictureFile != null && !profilePictureFile.isEmpty()) {
	            profilePictureFileName = profilePictureFile.getOriginalFilename();
	            String profilePictureUploadDir = "C:/JobPortalBackend/demoJobportal/uploads/profile_pictures/";
	            Path profilePictureUploadPath = Paths.get(profilePictureUploadDir);

	            if (!Files.exists(profilePictureUploadPath)) {
	                Files.createDirectories(profilePictureUploadPath);
	            }

	            Path profilePicturePath = profilePictureUploadPath.resolve(profilePictureFileName);
	            Files.copy(profilePictureFile.getInputStream(), profilePicturePath, StandardCopyOption.REPLACE_EXISTING);
	        }

	        // Create new JobSeeker object with file paths (if available)
	        JobSeeker newJobSeeker = new JobSeeker(
	                0, firstName, lastName, emailAddress, password, confirmPassword,
	                phoneNumber, location, resumeFileName, profilePictureFileName, "jobSeeker", null, null, null
	        );

	        // Add the JobSeeker to the database using the service
	        JobSeeker dbJobSeeker = jobseekerService.addJobSeeker(newJobSeeker);

	        // Return the created JobSeeker details
	        return new ResponseEntity<>(dbJobSeeker, HttpStatus.CREATED);

	    } catch (IOException e) {
	        // Handle file saving errors
	        return new ResponseEntity<>(new ErrorResponse("File upload failed", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	    } catch (Exception e) {
	        // Handle any other errors
	        return new ResponseEntity<>(new ErrorResponse("Job Seeker Registration failed", e.getMessage(), null), HttpStatus.BAD_REQUEST);
	    }
	}

	
	
	//http://localhost:8086/jobSeeker/login
	 @GetMapping("/login")
	    public ResponseEntity<?> loginJobSeeker(
	            @RequestParam("emailAddress") String emailAddress,
	            @RequestParam("password") String password) {

	        try {
	            // Check if the job seeker exists by email
	            JobSeeker jobSeeker = jobseekerService.getJobSeekerByEmail(emailAddress);

	            // If no JobSeeker found, return bad request
	            if (jobSeeker == null) {
	                return new ResponseEntity<>(new ErrorResponse("Invalid email or password", "Job seeker not found", null),
	                        HttpStatus.BAD_REQUEST);
	            }

	            // Check if the password matches (This should ideally be hashed and checked)
	            if (!jobSeeker.getPassword().equals(password)) {
	                return new ResponseEntity<>(new ErrorResponse("Invalid email or password", "Incorrect password", null),
	                        HttpStatus.BAD_REQUEST);
	            }

	            // Return JobSeeker details on successful login
	            return new ResponseEntity<>(jobSeeker, HttpStatus.OK);

	        } catch (Exception e) {
	            // Handle any errors
	            return new ResponseEntity<>(new ErrorResponse("Login failed", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	//http://localhost:8086/jobSeeker/getjobseeker/{jobSeekerId}
	 @GetMapping("/getjobseeker/{jobSeekerId}")
	 public ResponseEntity<?> getJobSeekerById(
	         @PathVariable("jobSeekerId") Long jobSeekerId) {

	     try {
	         // Retrieve the JobSeeker by jobSeekerId
	         JobSeeker jobSeeker = jobseekerService.getJobSeekerById(jobSeekerId);

	         // If no JobSeeker found, return a "Not Found" response
	         if (jobSeeker == null) {
	             return new ResponseEntity<>(new ErrorResponse("Job seeker not found", "No job seeker found with the provided ID", null),
	                     HttpStatus.NOT_FOUND);
	         }

	         // Return the found JobSeeker details
	         return new ResponseEntity<>(jobSeeker, HttpStatus.OK);

	     } catch (Exception e) {
	         // Handle any errors
	         return new ResponseEntity<>(new ErrorResponse("Error retrieving job seeker", e.getMessage(), null),
	                 HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
	 
	 
	 @PutMapping("/update/{jobSeekerId}")
	 public ResponseEntity<?> updateJobSeeker(
	         @PathVariable("jobSeekerId") Long jobSeekerId,
	         @RequestParam(value = "firstName", required = false) String firstName,
	         @RequestParam(value = "lastName", required = false) String lastName,
	         @RequestParam(value = "emailAddress", required = false) String emailAddress,
	         @RequestParam(value = "password", required = false) String password,
	         @RequestParam(value = "phoneNumber", required = false) String phoneNumber,
	         @RequestParam(value = "location", required = false) String location,
	         @RequestParam(value = "file", required = false) MultipartFile resumeFile,
	         @RequestParam(value = "profilePicture", required = false) MultipartFile profilePictureFile) {

	     try {
	         // Retrieve the existing JobSeeker
	         JobSeeker existingJobSeeker = jobseekerService.getJobSeekerById(jobSeekerId);

	         // If JobSeeker does not exist, return an error
	         if (existingJobSeeker == null) {
	             return new ResponseEntity<>(new ErrorResponse("Job seeker not found", "No job seeker found with the provided ID", null),
	                     HttpStatus.NOT_FOUND);
	         }

	         // Update fields if they are provided in the request
	         if (firstName != null && !firstName.isEmpty()) {
	             existingJobSeeker.setFirstName(firstName);
	         }
	         if (lastName != null && !lastName.isEmpty()) {
	             existingJobSeeker.setLastName(lastName);
	         }
	         if (emailAddress != null && !emailAddress.isEmpty()) {
	             existingJobSeeker.setEmailAddress(emailAddress);
	         }
	         if (password != null && !password.isEmpty()) {
	             existingJobSeeker.setPassword(password);
	         }
	         if (phoneNumber != null && !phoneNumber.isEmpty()) {
	             existingJobSeeker.setPhoneNumber(phoneNumber);
	         }
	         if (location != null && !location.isEmpty()) {
	             existingJobSeeker.setLocation(location);
	         }

	         // Handle resume file upload if it's provided
	         String resumeFileName = existingJobSeeker.getResumePath(); // Keep the old one if no new file is provided
	         if (resumeFile != null && !resumeFile.isEmpty()) {
	             resumeFileName = resumeFile.getOriginalFilename();
	             String resumeUploadDir = "C:/JobPortalBackend/demoJobportal/uploads/resumes/";
	             Path resumeUploadPath = Paths.get(resumeUploadDir);
	             
	             if (!Files.exists(resumeUploadPath)) {
	                 Files.createDirectories(resumeUploadPath);
	             }

	             Path resumeFilePath = resumeUploadPath.resolve(resumeFileName);
	             Files.copy(resumeFile.getInputStream(), resumeFilePath, StandardCopyOption.REPLACE_EXISTING);
	         }

	         // Handle profile picture file upload if it's provided
	         String profilePictureFileName = existingJobSeeker.getProfilePicturePath(); // Keep the old one if no new file is provided
	         if (profilePictureFile != null && !profilePictureFile.isEmpty()) {
	             profilePictureFileName = profilePictureFile.getOriginalFilename();
	             String profilePictureUploadDir = "C:/JobPortalBackend/demoJobportal/uploads/profile_pictures/";
	             Path profilePictureUploadPath = Paths.get(profilePictureUploadDir);
	             
	             if (!Files.exists(profilePictureUploadPath)) {
	                 Files.createDirectories(profilePictureUploadPath);
	             }

	             Path profilePicturePath = profilePictureUploadPath.resolve(profilePictureFileName);
	             Files.copy(profilePictureFile.getInputStream(), profilePicturePath, StandardCopyOption.REPLACE_EXISTING);
	         }

	         // Set updated file names
	         existingJobSeeker.setResumePath(resumeFileName);
	         existingJobSeeker.setProfilePicturePath(profilePictureFileName);

	         // Save the updated JobSeeker object to the database
	         JobSeeker updatedJobSeeker = jobseekerService.updateJobSeeker(existingJobSeeker);

	         // Return the updated JobSeeker
	         return new ResponseEntity<>(updatedJobSeeker, HttpStatus.OK);

	     } catch (IOException e) {
	         // Handle file saving errors
	         return new ResponseEntity<>(new ErrorResponse("File upload failed", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     } catch (Exception e) {
	         // Handle any other errors
	         return new ResponseEntity<>(new ErrorResponse("Job Seeker Update failed", e.getMessage(), null), HttpStatus.BAD_REQUEST);
	     }
	 }

	// http://localhost:8086/jobSeeker/getAllJobSeekers
	 @GetMapping("/getAllJobSeekers")
	 public ResponseEntity<?> getAllJobSeekers() {
	     try {
	         // Retrieve all job seekers from the service
	         List<JobSeeker> jobSeekers = jobseekerService.getAllJobSeekers();

	         // If no job seekers are found, return an empty list
	         if (jobSeekers.isEmpty()) {
	             return new ResponseEntity<>(new ErrorResponse("No job seekers found", "There are no job seekers in the database.", null),
	                     HttpStatus.NOT_FOUND);
	         }

	         // Return the list of job seekers
	         return new ResponseEntity<>(jobSeekers, HttpStatus.OK);

	     } catch (Exception e) {
	         // Handle any errors
	         return new ResponseEntity<>(new ErrorResponse("Error retrieving job seekers", e.getMessage(), null),
	                 HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	 
	 
	 @PostMapping("/forgotPassword")
	 public ResponseEntity<?> forgotPassword(@RequestParam("emailAddress") String emailAddress) {
	     try {
	         // Check if the job seeker exists
	         JobSeeker jobSeeker = jobseekerService.getJobSeekerByEmail(emailAddress);
	         
	         if (jobSeeker == null) {
	             return new ResponseEntity<>(
	                 new ErrorResponse("Email not found", "No account found with this email address.", null),
	                 HttpStatus.BAD_REQUEST
	             );
	         }

	         // Generate a unique password reset token
	         String resetToken = UUID.randomUUID().toString();
	         jobSeeker.setResetToken(resetToken);
	         jobSeeker.setResetTokenExpiry(LocalDateTime.now().plusHours(1)); // Token valid for 1 hour

	         // Save the token and expiry to the database
	         jobseekerService.updateJobSeeker(jobSeeker);

	         // Construct the reset link
	         String resetLink = "http://localhost:8086/jobSeeker/resetPassword?token=" + resetToken;

	         // Send the password reset email
	         emailService.sendPasswordResetEmail(jobSeeker.getEmailAddress(), resetLink);

	         return new ResponseEntity<>(
	             new SuccessResponse("Password reset email sent", "A password reset link has been sent to your email address.", null),
	             HttpStatus.OK
	         );
	     } catch (Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<>(
	             new ErrorResponse("Forgot Password failed", e.getMessage(), null),
	             HttpStatus.INTERNAL_SERVER_ERROR
	         );
	     }
	 }

	 
	 @PostMapping("/resetPassword")
	 public ResponseEntity<?> resetPassword(
	     @RequestParam("token") String token,
	     @RequestParam("newPassword") String newPassword,
	     @RequestParam("confirmPassword") String confirmPassword) {
	     try {
	         // Validate the token
	         JobSeeker jobSeeker = jobseekerService.getJobSeekerByResetToken(token);

	         if (jobSeeker == null || jobSeeker.getResetTokenExpiry().isBefore(LocalDateTime.now())) {
	             return new ResponseEntity<>(
	                 new ErrorResponse("Invalid token", "The password reset token is invalid or expired.", null),
	                 HttpStatus.BAD_REQUEST
	             );
	         }

	         // Validate passwords match
	         if (!newPassword.equals(confirmPassword)) {
	             return new ResponseEntity<>(
	                 new ErrorResponse("Password mismatch", "The new password and confirm password do not match.", null),
	                 HttpStatus.BAD_REQUEST
	             );
	         }

	         // Update the password and clear the token
	         jobSeeker.setPassword(newPassword); // Make sure the password is hashed before saving
	         jobSeeker.setResetToken(null);
	         jobSeeker.setResetTokenExpiry(null);
	         jobseekerService.updateJobSeeker(jobSeeker);

	         return new ResponseEntity<>(
	             new SuccessResponse("Password reset successful", "Your password has been successfully reset.", null),
	             HttpStatus.OK
	         );
	     } catch (Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<>(
	             new ErrorResponse("Reset Password failed", e.getMessage(), null),
	             HttpStatus.INTERNAL_SERVER_ERROR
	         );
	     }
	 }

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 //http://localhost:8086/jobSeeker/scheduleInterview
	 @PostMapping("/scheduleInterview")
	    public ResponseEntity<?> scheduleInterview(@RequestBody InterviewDTO interviewDTO) {
	        try {
	            // Fetch JobSeeker and JobPost by IDs
	            JobSeeker jobSeeker = jobseekerService.getJobSeekerById(interviewDTO.getJobSeekerId());
	            JobPost jobPost = jobPostService.getJobPostById(interviewDTO.getJobPostId());

	            // If JobSeeker or JobPost not found, return an error response
	            if (jobSeeker == null || jobPost == null) {
	                return new ResponseEntity<>("JobSeeker or JobPost not found", HttpStatus.NOT_FOUND);
	            }

	            // Create new Interview object and save it
	            Interview interview = new Interview();
	            interview.setJobSeeker(jobSeeker);
	            interview.setJobPost(jobPost);
	            interview.setInterviewDate(interviewDTO.getInterviewDate());
	            interview.setInterviewLocation(interviewDTO.getInterviewLocation());
	            interview.setInterviewTime(interviewDTO.getInterviewTime());
	            interview.setInterviewMode(interviewDTO.getInterviewMode());
	            interview.setInterviewLink(interviewDTO.getInterviewLink());
	            interview.setInterviewer(interviewDTO.getInterviewer());
	            interview.setInterviewMaterial(interviewDTO.getInterviewMaterial());
	            interview.setInterviewDuration(interviewDTO.getInterviewDuration());

	            // Save the interview (this would involve an InterviewService that manages Interview data)
	            Interview savedInterview = interviewService.scheduleInterview(interview);

	            // Return the saved interview details in response
	            return new ResponseEntity<>(savedInterview, HttpStatus.CREATED);

	        } catch (Exception e) {
	            // Handle any errors that occur during the interview scheduling process
	            return new ResponseEntity<>("Error scheduling interview: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 
	 
	//http://localhost:8086/jobSeeker//getInterviewsByCompany/{companyId}
	 @GetMapping("/getInterviewsByCompany/{companyId}")
	    public ResponseEntity<?> getInterviewsByCompany(@PathVariable Long companyId) {
	        try {
	            // Fetch interviews for the given companyId
	            List<Interview> interviews = interviewService.getInterviewsByCompanyId(companyId);

	            // If no interviews found for the company, return a 404 response
	            if (interviews.isEmpty()) {
	                return new ResponseEntity<>("No interviews found for this company", HttpStatus.NOT_FOUND);
	            }

	            // Return the list of interviews
	            return new ResponseEntity<>(interviews, HttpStatus.OK);

	        } catch (Exception e) {
	            // Handle any errors
	            return new ResponseEntity<>("Error retrieving interviews: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	 

		//http://localhost:8086/jobSeeker/getAllScheduledInterviews
	 @GetMapping("/getAllScheduledInterviews")
	 public ResponseEntity<?> getAllScheduledInterviews() {
	     try {
	         // Fetch all interviews using the InterviewService
	         List<Interview> interviews = interviewService.getAllScheduledInterviews();

	         // Check if no interviews are found
	         if (interviews.isEmpty()) {
	             return new ResponseEntity<>("No interviews scheduled", HttpStatus.NOT_FOUND);
	         }

	         // Return the list of interviews
	         return new ResponseEntity<>(interviews, HttpStatus.OK);

	     } catch (Exception e) {
	         // Handle any errors that occur
	         return new ResponseEntity<>("Error retrieving scheduled interviews: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }
	 
	 
	 
	 
	 
	 ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	 
	 
	 
	//http://localhost:8086/jobSeeker/applyForJob
	 @PostMapping("/applyForJob")
	    public ResponseEntity<?> applyForJob(@RequestParam long jobPostId, @RequestParam long jobSeekerId) {
	        try {
	            // Fetch the JobSeeker and JobPost from the database
	            JobSeeker jobSeeker = jobseekerService.getJobSeekerById(jobSeekerId);
	            JobPost jobPost = jobPostService.getJobPostById(jobPostId);

	            // Check if job seeker and job post exist
	            if (jobSeeker == null || jobPost == null) {
	                return new ResponseEntity<>("Job Seeker or Job Post not found", HttpStatus.NOT_FOUND);
	            }

	            // Create a new application
	            Application application = new Application();
	            application.setJobSeeker(jobSeeker);
	            application.setJobPost(jobPost);
	            application.setApplicationDate(new Date());
	            application.setApplicationStatus("Pending");

	            // Save the application to the database
	            applicationService.saveApplication(application);

	            // Notify the company (optionally send an email here
	            return new ResponseEntity<>("Application submitted successfully", HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error applying for the job", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	///http://localhost:8086/jobSeeker/applicants?jobPostId=6
	 @GetMapping("/applicants")
	    public ResponseEntity<?> getApplicantsByJobPostId(@RequestParam long jobPostId) {
	        try {
	            List<Application> applicants = applicationService.getApplicantsByJobPost_Id(jobPostId);

	            // Check if any applicants are found
	            if (applicants.isEmpty()) {
	                return new ResponseEntity<>("No applicants found for this job post", HttpStatus.NOT_FOUND);
	            }

	            return new ResponseEntity<>(applicants, HttpStatus.OK);
	        } catch (Exception e) {
	            return new ResponseEntity<>("Error fetching applicants", HttpStatus.INTERNAL_SERVER_ERROR);
	        }
	    }
	 
	
	 
	///http://localhost:8086/jobSeeker/applicationsByJobSeeker?jobSeekerId=4
	 @GetMapping("/applicationsByJobSeeker")
	 public ResponseEntity<?> getApplicationsByJobSeekerId(@RequestParam long jobSeekerId) {
	     try {
	         // Fetch the list of applications by the Job Seeker ID
	         List<Application> applications = applicationService.getApplicationsByJobSeekerId(jobSeekerId);

	         // Check if any applications are found
	         if (applications.isEmpty()) {
	             return new ResponseEntity<>("No applications found for this Job Seeker", HttpStatus.NOT_FOUND);
	         }

	         return new ResponseEntity<>(applications, HttpStatus.OK);
	     } catch (Exception e) {
	         return new ResponseEntity<>("Error fetching applications", HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	
	 

	 
//	 @GetMapping("/applicationsByJobSeeker")
//	 public ResponseEntity<?> getApplicationsByJobSeekerId(@RequestParam long jobSeekerId) {
//	     try {
//	         // Fetch the list of applications by Job Seeker ID
//	         List<Application> applications = applicationService.getApplicationsByJobSeekerId(jobSeekerId);
//
//	         // Check if any applications are found
//	         if (applications.isEmpty()) {
//	             return new ResponseEntity<>("No applications found for this Job Seeker", HttpStatus.NOT_FOUND);
//	         }
//
//	         // Map and collect only required fields
//	         List<Map<String, Object>> response = applications.stream()
//	             .map(application -> {
//	                 Map<String, Object> appData = new HashMap<>();
//	                 appData.put("applicationId", application.getApplicationId());
//	                 appData.put("jobSeeker", new JobSeekerDTO(application.getJobSeeker()));
//	                 return appData;
//	             })
//	             .collect(Collectors.toList());
//
//	         return new ResponseEntity<>(response, HttpStatus.OK);
//	     } catch (Exception e) {
//	         return new ResponseEntity<>("Error fetching applications", HttpStatus.INTERNAL_SERVER_ERROR);
//	     }
//	 }
	 
	 
	// DELETE: http://localhost:8086/jobSeeker/deleteApplicant?applicationId=3
	 @DeleteMapping("/deleteApplicant")
	 public ResponseEntity<?> deleteApplicantByApplicationId(@RequestParam long applicationId) {
	     try {
	         // Fetch the application by ID
	         Application application = applicationService.getApplicationById(applicationId);

	         // Check if the application exists
	         if (application == null) {
	             return new ResponseEntity<>("Application not found", HttpStatus.NOT_FOUND);
	         }

	         // Delete the application
	         applicationService.deleteApplication(applicationId);

	         return new ResponseEntity<>("Application deleted successfully", HttpStatus.OK);
	     } catch (Exception e) {
	         return new ResponseEntity<>("Error deleting application", HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	 
	 ///////////////////////////////////////////////////////////////////////////////////////////////////
	 
	//http://localhost:8086/jobSeeker/addSkill/{jobSeekerId}
	 @PostMapping("/addSkill/{jobSeekerId}")
	 public ResponseEntity<?> addSkill(@PathVariable Long jobSeekerId, @RequestBody Skill skill) {
	     try {
	         Skill addedSkill = jobseekerService.addSkill(jobSeekerId, skill);
	         return new ResponseEntity<>(addedSkill, HttpStatus.CREATED);
	     } catch (Exception e) {
	         return new ResponseEntity<>(new ErrorResponse("Error adding skill", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	 
		//http://localhost:8086/jobSeeker/addEducation/{jobSeekerId}
	 @PostMapping("/addEducation/{jobSeekerId}")
	 public ResponseEntity<?> addEducation(@PathVariable Long jobSeekerId, @RequestBody Education education) {
	     try {
	         Education addedEducation = jobseekerService.addEducation(jobSeekerId, education);
	         return new ResponseEntity<>(addedEducation, HttpStatus.CREATED);
	     } catch (Exception e) {
	         return new ResponseEntity<>(new ErrorResponse("Error adding education", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	//http://localhost:8086/jobSeeker/addProject/{jobSeekerId}
	 @PostMapping("/addProject/{jobSeekerId}")
	 public ResponseEntity<?> addProject(@PathVariable Long jobSeekerId, @RequestBody Project project) {
	     try {
	         Project addedProject = jobseekerService.addProject(jobSeekerId, project);
	         return new ResponseEntity<>(addedProject, HttpStatus.CREATED);
	     } catch (Exception e) {
	         return new ResponseEntity<>(new ErrorResponse("Error adding project", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	//http://localhost:8086/jobSeeker/getSkills/{jobSeekerId}
	 @GetMapping("/getSkills/{jobSeekerId}")
	 public ResponseEntity<?> getSkills(@PathVariable Long jobSeekerId) {
	     try {
	         List<Skill> skills = jobseekerService.getSkills(jobSeekerId);
	         return new ResponseEntity<>(skills, HttpStatus.OK);
	     } catch (Exception e) {
	         return new ResponseEntity<>(new ErrorResponse("Error retrieving skills", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	//http://localhost:8086/jobSeeker/getEducation/{jobSeekerId}
	 @GetMapping("/getEducation/{jobSeekerId}")
	 public ResponseEntity<?> getEducation(@PathVariable Long jobSeekerId) {
	     try {
	         List<Education> education = jobseekerService.getEducation(jobSeekerId);
	         return new ResponseEntity<>(education, HttpStatus.OK);
	     } catch (Exception e) {
	         return new ResponseEntity<>(new ErrorResponse("Error retrieving education", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

		//http://localhost:8086/jobSeeker/getProjects/{jobSeekerId}
	 @GetMapping("/getProjects/{jobSeekerId}")
	 public ResponseEntity<?> getProjects(@PathVariable Long jobSeekerId) {
	     try {
	         List<Project> projects = jobseekerService.getProjects(jobSeekerId);
	         return new ResponseEntity<>(projects, HttpStatus.OK);
	     } catch (Exception e) {
	         return new ResponseEntity<>(new ErrorResponse("Error retrieving projects", e.getMessage(), null), HttpStatus.INTERNAL_SERVER_ERROR);
	     }
	 }

	    
	 
}
