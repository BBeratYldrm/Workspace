package com.berat.service.user.Impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.user.UserRepository;
import com.berat.dao.user.VerificationTokenRepository;
import com.berat.domain.user.User;
import com.berat.domain.user.VerificationToken;
import com.berat.service.user.VerificationTokenService;

@Service
public class VerificationTokenServiceImpl implements VerificationTokenService {

	@Autowired
	private VerificationTokenRepository verificationTokenRepository;

	@Autowired
	private UserRepository userRepository;

	public static final String TOKEN_INVALID = "InvalidToken";
	public static final String TOKEN_EXPIRED = "ExpiredToken";
	public static final String TOKEN_VALID = "ValidToken";

	@Override
	public String validateVerificationToken(String token) {

		VerificationToken verificationToken = verificationTokenRepository.findVerificationTokenByToken(token);
		if (verificationToken == null) {
			return TOKEN_INVALID;
		}

		Calendar calendar = Calendar.getInstance();
		if (verificationToken.getExpiryDate().getTime() - calendar.getTime().getTime() <= 0) {
			return TOKEN_EXPIRED;
		}
		User user = verificationToken.getUser();
		user.setEnabled(true);
		userRepository.updateUser(user);
		return TOKEN_VALID;
	}

	@Override
	public VerificationToken saveVerificationToken(VerificationToken verificationToken) {
		return verificationTokenRepository.saveVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken updateVerificationToken(VerificationToken verificationToken) {
		return verificationTokenRepository.updateVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken deleteVerificationToken(VerificationToken verificationToken) {
		return verificationTokenRepository.deleteVerificationToken(verificationToken);
	}

	@Override
	public VerificationToken findVerificationTokenByToken(String token) {
		return verificationTokenRepository.findVerificationTokenByToken(token);
	}

	@Override
	public VerificationToken findVerificationTokenByUser(User user) {
		return verificationTokenRepository.findVerificationTokenByUser(user);
	}

	@Override
	public List<VerificationToken> findAllExpiredToken(Date date) {
		return verificationTokenRepository.findAllExpiredToken(date);
	}

	@Override
	public boolean deleteAllExpiresTokens(Date date) {
		return verificationTokenRepository.deleteAllExpiresTokens(date);
	}

}
