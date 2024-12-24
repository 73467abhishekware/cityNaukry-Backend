package com.hundia.cityNaukry.service;

import java.util.List;

import com.hundia.cityNaukry.pojo.Company;
import com.hundia.cityNaukry.pojo.JobPost;

public interface IJobPostService {

	JobPost addPost(JobPost jobPost);

	List<JobPost> getAllPosts();

	List<JobPost> getPostsByCompany(Company company);

	JobPost getJobPostById(long jobPostId);

	void deletePost(long jobPostId);

	JobPost updatePost(JobPost existingJobPost);


}
