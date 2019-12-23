package com.berat.service.user;

import java.util.Date;
import java.util.List;

import com.berat.domain.user.User;
import com.berat.domain.user.VerificationToken;

public interface VerificationTokenService {

	VerificationToken saveVerificationToken(VerificationToken verificationToken);

	VerificationToken updateVerificationToken(VerificationToken verificationToken);

	VerificationToken deleteVerificationToken(VerificationToken verificationToken);

	VerificationToken findVerificationTokenByToken(String token);

	VerificationToken findVerificationTokenByUser(User user);

	List<VerificationToken> findAllExpiredToken(Date date);

	boolean deleteAllExpiresTokens(Date date);

	String validateVerificationToken(String token);

}
