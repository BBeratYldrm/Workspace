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

import com.berat.dao.user.VerificationTokenRepository;
import com.berat.domain.user.User;
import com.berat.domain.user.VerificationToken;

@Repository
@Transactional
public class VerificationTokenRepositoryImpl implements VerificationTokenRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
		entityManager.persist(verificationToken);
		return null;
	}

	@Override
	public VerificationToken updateVerificationToken(VerificationToken verificationToken) {
		VerificationToken updatedVerificationToken = entityManager.merge(verificationToken);
		entityManager.flush();
		return updatedVerificationToken;
	}

	@Override
	public VerificationToken deleteVerificationToken(VerificationToken verificationToken) {
		if (entityManager.contains(verificationToken)) {
			entityManager.remove(verificationToken);
			return verificationToken;
		} else {
			VerificationToken deletedVerificationToken = findVerificationTokenByToken(verificationToken.getToken());
			entityManager.remove(deletedVerificationToken);
			return deletedVerificationToken;
		}
	}

	@Override
	public VerificationToken findVerificationTokenByToken(String token) {
		return entityManager.createNamedQuery("VerificationToken.findByToken", VerificationToken.class)
				.setParameter("token", token).getSingleResult();
	}

	@Override
	public VerificationToken findVerificationTokenByUser(User user) {
		return entityManager.createNamedQuery("VerificationToken.findByUserId", VerificationToken.class)
				.setParameter("userId", user.getUserId()).getSingleResult();
	}

	@Override
	public List<VerificationToken> findAllExpiredToken(Date date) {
		TypedQuery<VerificationToken> typedQuery = entityManager
				.createNamedQuery("VerificationToken.findAllByExpiredTokens", VerificationToken.class);
		typedQuery.setParameter("expiryDate", date);
		return typedQuery.getResultList();
	}

	@Override
	public boolean deleteAllExpiresTokens(Date date) {
		Query query = entityManager.createNamedQuery("VerificationToken.deleteAllExpiredTokens");
		query.setParameter("date", date, TemporalType.TIMESTAMP);
		int i = query.executeUpdate();
		return i != -1;
	}

}
