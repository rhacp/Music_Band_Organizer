package com.anghel.music_band_organizer.validations.state;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;

public class EnumStatePatternValidator implements ConstraintValidator<EnumStatePattern, String> {

    private String[] subset;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        return value == null || Arrays.asList(subset).contains(value.toLowerCase());
    }

    @Override
    public void initialize(EnumStatePattern constraintAnnotation) {
        this.subset = constraintAnnotation.anyOf();
    }
}
