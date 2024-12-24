package com.hundia.cityNaukry.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hundia.cityNaukry.pojo.Skill;

public interface SkillRepository  extends JpaRepository<Skill, Long> {

	List<Skill> findByJobSeeker_JobSeekerId(Long jobSeekerId);

}
