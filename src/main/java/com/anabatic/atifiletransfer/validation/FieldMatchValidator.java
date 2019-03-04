package com.anabatic.atifiletransfer.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class FieldMatchValidator implements ConstraintValidator<FieldMatch, Object> {

	private String firstField;
    private String secondField;
    private String message;
	
	@Override
	public void initialize(FieldMatch constraintAnnotation) {
		firstField = constraintAnnotation.first();
		secondField = constraintAnnotation.second();
        message = constraintAnnotation.message();
	}

	@Override
    public boolean isValid(final Object value, final ConstraintValidatorContext context) {
        boolean valid = true;
        try
        {
            final Object firstObj = BeanUtils.getProperty(value, firstField);
            final Object secondObj = BeanUtils.getProperty(value, secondField);
            
            if(firstObj.toString().length()>15) {
            	valid = BCrypt.checkpw(secondObj.toString(), firstObj.toString());
            } else {
            	valid =  firstObj == null && secondObj == null || firstObj != null && firstObj.equals(secondObj);
            } 
        }
        catch (final Exception ignore)
        {
            // ignore
        }

        if (!valid){
            context.buildConstraintViolationWithTemplate(message)
                    .addPropertyNode(secondField)
                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
        }

        return valid;
    }

}
