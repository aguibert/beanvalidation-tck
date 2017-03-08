/**
 * Bean Validation TCK
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.beanvalidation.tck.tests.constraints.invalidconstraintdefinitions;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @author Gunnar Morling
 */
@Documented
@Constraint(validatedBy = {
		ConstraintWithTwoCrossParameterValidators.Validator.class,
		ConstraintWithTwoCrossParameterValidators.AnotherValidator.class
})
@Target({ METHOD })
@Retention(RUNTIME)
public @interface ConstraintWithTwoCrossParameterValidators {
	String message() default "default message";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };

	@SupportedValidationTarget(value = ValidationTarget.PARAMETERS)
	public static class Validator
			implements ConstraintValidator<ConstraintWithTwoCrossParameterValidators, Object[]> {

		@Override
		public boolean isValid(Object[] parameters, ConstraintValidatorContext constraintValidatorContext) {
			return false;
		}
	}

	@SupportedValidationTarget(value = ValidationTarget.PARAMETERS)
	public static class AnotherValidator
			implements ConstraintValidator<ConstraintWithTwoCrossParameterValidators, Object[]> {

		@Override
		public boolean isValid(Object[] parameters, ConstraintValidatorContext constraintValidatorContext) {
			return false;
		}
	}
}