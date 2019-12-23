package com.berat.web.DataTO;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.berat.validation.ValidMatchesPassword;
import com.berat.validation.ValidEmail;
import com.berat.validation.ValidConstraintPassword;

@ValidMatchesPassword
public class UserDataTO {

	@NotNull
	@Size(min = 3, max = 15, message = "Adýnýz 3 karakterden az 15 karakterden çok olamaz.")
	private String firstName;

	private String lastName;

	private String userName;

	@ValidEmail
	private String eMail;

	@ValidConstraintPassword
	private String password;

	private String matchingPassword;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String geteMail() {
		return eMail;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMatchingPassword() {
		return matchingPassword;
	}

	public void setMatchingPassword(String matchingPassword) {
		this.matchingPassword = matchingPassword;
	}

}
