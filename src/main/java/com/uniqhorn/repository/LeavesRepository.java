package com.uniqhorn.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uniqhorn.entity.Leave;
import com.uniqhorn.entity.User;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

public interface LeavesRepository extends JpaRepository<Leave, Integer> {

	@Transactional
	Leave findById(long Id);

	@Transactional
	void deleteById(Long Id);

	Set<Leave> findByUser(User user);

	@Transactional
	void deleteByUser(User user);

	@Transactional
	Leave deleteByLeaveDate(Date date);

}
