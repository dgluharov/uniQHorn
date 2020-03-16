package com.uniqhorn.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uniqhorn.entity.Work;

public interface WorkRepository extends JpaRepository<Work, Long> {

	@Transactional
	void deleteById(long workId);

	
}
