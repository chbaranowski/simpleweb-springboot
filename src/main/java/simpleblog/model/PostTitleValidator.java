package simpleblog.model;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

public class PostTitleValidator implements ConstraintValidator<Title, String>{

	@Override
	public void initialize(Title constraintAnnotation) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(StringUtils.equals(value, "test")) {
			return false;
		}
		return true;
	}

}
