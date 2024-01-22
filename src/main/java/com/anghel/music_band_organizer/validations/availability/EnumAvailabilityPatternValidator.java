package com.anghel.music_band_organizer.validations.availability;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumAvailabilityPatternValidator implements ConstraintValidator<EnumAvailabilityPattern, String> {

    private String[] subset;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value.toLowerCase());
    }

    @Override
    public void initialize(EnumAvailabilityPattern constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }
}
