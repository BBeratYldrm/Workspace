package com.berat.service.user;

import java.util.Date;
import java.util.List;

import com.berat.domain.user.PasswordResetToken;
import com.berat.domain.user.User;

public interface PasswordResetTokenService {

	PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken);

	PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken);

	PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken);

	PasswordResetToken findPasswordResetTokenByToken(String token);

	PasswordResetToken findPasswordResetTokenByUser(User user);

	PasswordResetToken findPasswordResetTokenFindById(long id);

	List<PasswordResetToken> findAllByExpiredTokens(Date date);

	boolean deleteAllExpiredTokens(Date date);

}
