package com.anghel.music_band_organizer.validations.role;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumRolePatternValidator implements ConstraintValidator<EnumRolePattern, String> {

    private String[] subset;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value.toLowerCase());
    }

    @Override
    public void initialize(EnumRolePattern constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }
}
