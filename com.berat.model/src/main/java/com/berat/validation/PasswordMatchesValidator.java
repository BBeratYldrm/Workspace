package com.berat.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.berat.web.DataTO.PasswordDataTO;
import com.berat.web.DataTO.UserDataTO;

public class PasswordMatchesValidator implements ConstraintValidator<ValidMatchesPassword, Object> {

	@Override
	public void initialize(ValidMatchesPassword constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(Object passWord, ConstraintValidatorContext context) {

		if (passWord.getClass().getSimpleName().equals("UserDataTO")) {

			UserDataTO userDataTO = (UserDataTO) passWord;
			return userDataTO.getPassword().equals(userDataTO.getMatchingPassword());

		} else {

			PasswordDataTO passwordDataTO = (PasswordDataTO) passWord;
			return passwordDataTO.getNewPassword().equals(passwordDataTO.getMatchNewPassword());

		}
	}

}
