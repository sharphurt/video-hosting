package ru.sharphurt.videohosting.validation.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EvenNumberValidator implements ConstraintValidator<EvenNumber, Integer> {

    @Override
    public boolean isValid(Integer s, ConstraintValidatorContext constraintValidatorContext) {
        return s % 2 == 0;
    }
}
