package be.bstorm.tf_java2025_xface_api.api.validators;

import be.bstorm.tf_java2025_xface_api.api.validators.annotations.AgeMin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.*;

public class AgeMinValidator implements ConstraintValidator<AgeMin, LocalDate> {

    private int min;

    @Override
    public void initialize(AgeMin constraintAnnotation) {
        this.min = constraintAnnotation.min();
    }

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {

        if (value == null) return true;

        LocalDate now = LocalDate.now();
        int age = Period.between(value, now).getYears();

        return age >= min;
    }
}
