package ru.kpfu.itis.nasibullin.medokspring.validation.password;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class PasswordsValidator implements ConstraintValidator<PasswordsValidation, Object> {
    private String password;
    private String repeatedPassword;

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        Object passwordValue = new BeanWrapperImpl(o)
          .getPropertyValue(password);
        Object repeatedPasswordValue = new BeanWrapperImpl(o)
          .getPropertyValue(repeatedPassword);

        if (passwordValue != null) {
            return passwordValue.equals(repeatedPasswordValue);
        } else {
            return repeatedPasswordValue == null;
        }
    }

    @Override
    public void initialize(PasswordsValidation constraintAnnotation) {
        password = constraintAnnotation.password();
        repeatedPassword = constraintAnnotation.repeatedPassword();
    }
}
