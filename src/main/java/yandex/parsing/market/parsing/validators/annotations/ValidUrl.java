package yandex.parsing.market.parsing.validators.annotations;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import yandex.parsing.market.parsing.validators.realizations.UrlValidator;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UrlValidator.class)
@Target(ElementType.FIELD)
public @interface ValidUrl {
    String message() default "Invalid URL";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}