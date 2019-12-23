package com.berat.service.user.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.berat.dao.user.PasswordResetTokenRepository;
import com.berat.domain.user.PasswordResetToken;
import com.berat.domain.user.User;
import com.berat.service.user.PasswordResetTokenService;

@Service
public class PasswordResetTokenImpl implements PasswordResetTokenService {

	@Autowired
	private PasswordResetTokenRepository passwordResetTokenRepository;

	@Override
	public PasswordResetToken savePasswordResetToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.savePasswordResetToken(passwordResetToken);
	}

	@Override
	public PasswordResetToken updatePasswordResetToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.updatePasswordResetToken(passwordResetToken);
	}

	@Override
	public PasswordResetToken deletePasswordResetToken(PasswordResetToken passwordResetToken) {
		return passwordResetTokenRepository.deletePasswordResetToken(passwordResetToken);
	}

	@Override
	public PasswordResetToken findPasswordResetTokenByToken(String token) {
		return passwordResetTokenRepository.findPasswordResetTokenByToken(token);
	}

	@Override
	public PasswordResetToken findPasswordResetTokenByUser(User user) {
		return passwordResetTokenRepository.findPasswordResetTokenByUser(user);
	}

	@Override
	public PasswordResetToken findPasswordResetTokenFindById(long id) {
		return passwordResetTokenRepository.findPasswordResetTokenFindById(id);
	}

	@Override
	public List<PasswordResetToken> findAllByExpiredTokens(Date date) {
		return passwordResetTokenRepository.findAllByExpiredTokens(date);
	}

	@Override
	public boolean deleteAllExpiredTokens(Date date) {
		return passwordResetTokenRepository.deleteAllExpiredTokens(date);
	}

}
