package com.uniqhorn.dao;

import com.uniqhorn.entity.Work;
import com.uniqhorn.repository.WorkRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class WorkDAO {

	@Autowired
	WorkRepository workRepository;

	public void save(Work newWork) {
		workRepository.save(newWork);
	}

	public void deleteById(long workId) {
		workRepository.deleteById(workId);
	}

	public Work findById(long workId) {
		return workRepository.findById(workId).get();
	}


}
