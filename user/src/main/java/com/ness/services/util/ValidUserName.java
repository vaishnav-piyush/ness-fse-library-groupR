package com.ness.services.util;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Created by p7109792 on 11/19/17.
 */
@Target({ FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = ValidateUserName.class)
@Documented
public @interface ValidUserName {
    String message() default "Username should be unique";
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
