package com.berat.dao.user.Impl;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.berat.dao.user.PasswordResetTokenRepository;
import com.berat.domain.user.PasswordResetToken;
import com.berat.domain.user.User;

@Repository
@Transactional
public class PasswordResetTokenImpl implements PasswordResetTokenRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken) {
		entityManager.persist(passwordResetToken);
		return null;
	}

	@Override
	public PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken) {
		PasswordResetToken updatedPasswordResetToken = entityManager.merge(passwordResetToken);
		entityManager.flush();
		return updatedPasswordResetToken;
	}

	@Override
	public PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken) {
		if (entityManager.contains(passwordResetToken)) {
			entityManager.remove(passwordResetToken);
			return passwordResetToken;
		}
		PasswordResetToken deletePasswordResetToken = findPasswordResetTokenFindById(passwordResetToken.getId());
		entityManager.remove(deletePasswordResetToken);
		return deletePasswordResetToken;

	}

	@Override
	public PasswordResetToken findPasswordResetTokenByToken(String token) {
		TypedQuery<PasswordResetToken> typedQuery = entityManager.createNamedQuery("PasswordResetToken.findByToken",
				PasswordResetToken.class);
		typedQuery.setParameter("token", token);
		return typedQuery.getSingleResult();
	}

	@Override
	public PasswordResetToken findPasswordResetTokenByUser(User user) {
		TypedQuery<PasswordResetToken> typedQuery = entityManager.createNamedQuery("PasswordResetToken.findByUserId",
				PasswordResetToken.class);
		typedQuery.setParameter("userId", user.getUserId());
		return typedQuery.getSingleResult();
	}

	@Override
	public PasswordResetToken findPasswordResetTokenFindById(long id) {

		return entityManager.find(PasswordResetToken.class, id);
	}

	@Override
	public List<PasswordResetToken> findAllByExpiredTokens(Date date) {
		TypedQuery<PasswordResetToken> typedQuery = entityManager
				.createNamedQuery("PasswordResetToken.deleteAllExpiredTokens", PasswordResetToken.class);
		typedQuery.setParameter("expiryDate", date, TemporalType.TIMESTAMP);
		return typedQuery.getResultList();
	}

	@Override
	public boolean deleteAllExpiredTokens(Date date) {
		Query query = entityManager.createNamedQuery("PasswordResetToken.findAllByExpiredTokens");
		query.setParameter("date", date, TemporalType.TIMESTAMP);
		int i = query.executeUpdate();
		return i != -1;
	}

}
