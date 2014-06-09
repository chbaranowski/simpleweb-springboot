package simpleblog.domain

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;

import javax.validation.Constraint;
import javax.validation.Payload;

@Retention(RUNTIME)
@Constraint(validatedBy = PostTitleValidator.class)
@interface Title {
	String message() default "{simpleblog.model.Post.title}";
	Class<?>[] groups() default [];
	Class<? extends Payload>[] payload() default [];
}
