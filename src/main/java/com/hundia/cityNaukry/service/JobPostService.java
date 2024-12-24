package com.hundia.cityNaukry.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hundia.cityNaukry.dao.JobPostRepository;
import com.hundia.cityNaukry.pojo.Company;
import com.hundia.cityNaukry.pojo.JobPost;

@Service
public class JobPostService implements IJobPostService{

	@Autowired
	 private JobPostRepository jobPostRepo;
	
	@Override
	public JobPost addPost(JobPost jobPost) {
		// TODO Auto-generated method stub
		return jobPostRepo.save(jobPost);
	}

	@Override
	public List<JobPost> getAllPosts() {
		// TODO Auto-generated method stub
		return jobPostRepo.findAll();
	}

	@Override
	public List<JobPost> getPostsByCompany(Company company) {
		 return jobPostRepo.findByCompany(company);
	}

	@Override
	public JobPost getJobPostById(long jobPostId) {
		 return jobPostRepo.findById(jobPostId).orElse(null);
	}

	@Override
	public void deletePost(long jobPostId) {
	    try {
	        // Check if the job post exists
	        JobPost jobPost = jobPostRepo.findById(jobPostId)
	                                      .orElseThrow(() -> new RuntimeException("Job post not found"));
	        
	        // Delete the job post
	        jobPostRepo.delete(jobPost);
	    } catch (Exception e) {
	        // You could log the error if needed
	        throw new RuntimeException("Error while deleting job post: " + e.getMessage());
	    }
	}

	@Override
	public JobPost updatePost(JobPost existingJobPost) {
	    try {
	        // Check if the existing job post exists in the database
	        if (existingJobPost != null) {
	            // Save the updated job post to the database
	            JobPost updatedJobPost = jobPostRepo.save(existingJobPost);

	            // Return the updated job post
	            return updatedJobPost;
	        } else {
	            throw new Exception("JobPost does not exist in the database.");
	        }
	    } catch (Exception e) {
	        // Handle any exceptions that occur
	        throw new RuntimeException("Failed to update job post: " + e.getMessage());
	    }
	}






}
