package com.berat.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.passay.DigitCharacterRule;
import org.passay.LengthRule;
import org.passay.PasswordData;
import org.passay.PasswordValidator;
import org.passay.Rule;
import org.passay.RuleResult;
import org.passay.SpecialCharacterRule;
import org.passay.UppercaseCharacterRule;
import org.passay.WhitespaceRule;

import com.google.common.base.Joiner;

public class PasswordConstraintValidator implements ConstraintValidator<ValidConstraintPassword, String> {

	@Override
	public void initialize(ValidConstraintPassword constraintAnnotation) {

		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String passWord, ConstraintValidatorContext context) {
		List<Rule> rules = new ArrayList<>();
		rules.add(new LengthRule(5, 20));
		rules.add(new UppercaseCharacterRule(1));
		rules.add(new DigitCharacterRule(1));
		rules.add(new WhitespaceRule());
		rules.add(new SpecialCharacterRule(1));

		PasswordValidator passwordValidator = new PasswordValidator(rules);
		RuleResult result = passwordValidator.validate(new PasswordData(passWord));
		if (result.isValid()) {
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(Joiner.on("\n").join(passwordValidator.getMessages(result)))
				.addConstraintViolation();
		return false;
	}

}
