package com.stanostr.springjdbc.security;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {
    public void initialize(final FieldMatch constraintAnnotation) {

    }
    
	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		try {     
			RegistrationForm form = (RegistrationForm) value;
			if(form.getPassword()==null || form.getConfirm()==null) return true;
	        return form.getPassword().equals(form.getConfirm());
		} catch (Exception e) {
			return false;
	    }
	}

}
