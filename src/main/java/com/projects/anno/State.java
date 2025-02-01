package com.projects.anno;

import com.projects.validation.StateValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented //Specify if this annotation can be extracted to the Documentation
@Target({ElementType.FIELD}) //Define where the annotation can be used
@Retention(RetentionPolicy.RUNTIME) //Specify when the annotation function can be last until
@Constraint(validatedBy = {StateValidator.class}) //Specify which verify class to use
public @interface State {

    // Error Message when fail to validate
    String message() default "State can only be Published or Draft";

    // Which validation group it belongs to
    Class<?>[] groups() default {};

    // Additional attached information for the annotation
    Class<? extends Payload>[] payload() default {};
}
