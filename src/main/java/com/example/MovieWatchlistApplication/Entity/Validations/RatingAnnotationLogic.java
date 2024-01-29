package com.example.MovieWatchlistApplication.Entity.Validations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RatingAnnotationLogic implements ConstraintValidator<Rating, Float> {

    @Override
    public boolean isValid(Float value, ConstraintValidatorContext constraintValidatorContext) {
        return value>5 && value<=10;
    }
}
