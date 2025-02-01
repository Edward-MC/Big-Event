package com.projects.validation;

import com.projects.anno.State;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class StateValidator implements ConstraintValidator<State, String> {
    /**
     *
     * @param value Data to be Verified
     * @param constraintValidatorContext
     * @return If return True, pass validation. If false, then fail.
     */
    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        if (value == null) {
            return false;
        }

        if ("PUBLISHED".equalsIgnoreCase(value) || "DRAFT".equalsIgnoreCase(value)) {
            return true;
        }
        return false;
    }
}
