package com.berat.web.DataTO;

import com.berat.validation.ValidMatchesPassword;
import com.berat.validation.ValidConstraintPassword;

@ValidMatchesPassword
public class PasswordDataTO {

	private String oldPassword;

	@ValidConstraintPassword
	private String newPassword;

	private String matchNewPassword;

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getMatchNewPassword() {
		return matchNewPassword;
	}

	public void setMatchNewPassword(String matchNewPassword) {
		this.matchNewPassword = matchNewPassword;
	}

}
