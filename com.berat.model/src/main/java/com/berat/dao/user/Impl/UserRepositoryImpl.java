package com.berat.dao.user.Impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.user.UserRepository;
import com.berat.domain.user.User;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public User saveUser(User user) {
		entityManager.persist(user);
		return user;
	}

	@Override
	public User updateUser(User user) {
		User updatedUser = entityManager.merge(user);
		entityManager.flush();
		return updatedUser;
	}

	@Override
	public User deleteUser(User user) {
		if (entityManager.contains(user)) {
			entityManager.remove(user);
			return user;
		} else {
			User deleteUser = findUserById(user.getUserId());
			entityManager.remove(deleteUser);
			return deleteUser;
		}
	}

	@Override
	public User findUserById(long id) {
		return entityManager.find(User.class, id);
	}

	@Override
	public User findUserByEmail(String eMail) {
		return entityManager.createNamedQuery("User.findUserByEmail", User.class).setParameter("eMail", eMail)
				.getSingleResult();
	}

	@Override
	public User findUserByName(String userName) {
		return entityManager.createNamedQuery("User.findUserByUsername", User.class).setParameter("userName", userName)
				.getSingleResult();
	}

	@Override
	public List<User> findAllUsers() {
		return entityManager.createNamedQuery("User.findAllUsers", User.class).getResultList();
	}

}
