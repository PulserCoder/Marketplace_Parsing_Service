package yandex.parsing.market.parsing.validators.realizations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import yandex.parsing.market.parsing.validators.annotations.ValidUrl;

import java.lang.annotation.Annotation;
import java.net.MalformedURLException;
import java.net.URL;

public class UrlValidator implements ConstraintValidator<ValidUrl, String> {

    @Override
    public void initialize(ValidUrl constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.isEmpty()) {
            return false;
        }
        try {
            new URL(s);

        } catch (MalformedURLException e) {
            return false;
        }
        return s.contains("https://market.yandex.ru/");
    }
}
