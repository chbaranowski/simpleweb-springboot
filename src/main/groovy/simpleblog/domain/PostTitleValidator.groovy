package simpleblog.domain

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.lang.StringUtils;

class PostTitleValidator implements ConstraintValidator<Title, String> {

	@Override
	void initialize(Title constraintAnnotation) {
	}

	@Override
	boolean isValid(String value, ConstraintValidatorContext context) {
		if (StringUtils.equals(value, "test")) {
			return false;
		}
		return true;
	}

}
