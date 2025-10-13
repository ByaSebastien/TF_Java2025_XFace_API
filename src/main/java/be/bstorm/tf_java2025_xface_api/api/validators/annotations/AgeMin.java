package be.bstorm.tf_java2025_xface_api.api.validators.annotations;

import be.bstorm.tf_java2025_xface_api.api.validators.AgeMinValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = {AgeMinValidator.class})
public @interface AgeMin {

    int min() default 18;

    String message() default "Minimum {min}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
