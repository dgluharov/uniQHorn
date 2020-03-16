package com.uniqhorn.dao;

import com.uniqhorn.entity.Leave;
import com.uniqhorn.entity.User;
import com.uniqhorn.repository.LeavesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Repository
public class LeavesDAO {

	@PersistenceContext
	private EntityManager em;

	@Autowired
	LeavesRepository leavesRepository;

	// Save leave
	public Leave save(Leave leave) {
		return leavesRepository.save(leave);
	}

	// Find All Users
	public List<Leave> findAll() {
		return leavesRepository.findAll();
	}

	// Find Leave By id
	public Leave findById(long id) {
		return leavesRepository.findById(id);
	}

	// Delete User By id

	public void deleteById(Long id) {
		leavesRepository.deleteById(id);

	}

	@Transactional
	public Leave updateById(Integer Id, Leave newLeave) {

		long TheId = findById(Id).getId();
		newLeave.setId(Math.toIntExact(TheId));
		return em.merge(newLeave);
	}

	public Set<Leave> getUserLeaves(User user) {
		return leavesRepository.findByUser(user);
	}

	public void deleteByUser(User user) {
		leavesRepository.deleteByUser(user);
	}

}
