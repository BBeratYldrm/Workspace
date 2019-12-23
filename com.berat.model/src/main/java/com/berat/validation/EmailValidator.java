package com.berat.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

	private Pattern pattern;

	private Matcher matcher;

	public static final String EMAIL_VERIFICATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";

	@Override
	public void initialize(ValidEmail constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String eMail, ConstraintValidatorContext context) {
		pattern = Pattern.compile(EMAIL_VERIFICATION);

		matcher = pattern.matcher(eMail);

		return matcher.matches();
	}

}
