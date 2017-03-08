/**
 * Bean Validation TCK
 *
 * License: Apache License, Version 2.0
 * See the license.txt file in the root directory or <http://www.apache.org/licenses/LICENSE-2.0>.
 */
package org.hibernate.beanvalidation.tck.tests.constraints.inheritance.method.validdeclarations.constraint;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;

/**
 * @author Gunnar Morling
 */
@Target({ CONSTRUCTOR, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidAbstractCalendarServiceValidator.class)
@Documented
public @interface ValidAbstractCalendarService {
	String message() default "{ValidAbstractCalendarService.message}";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}