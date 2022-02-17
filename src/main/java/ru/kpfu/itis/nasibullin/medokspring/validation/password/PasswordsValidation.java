package ru.kpfu.itis.nasibullin.medokspring.validation.password;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Payload;

public @interface PasswordsValidation {
    String message() default "Пароли не совпадают";
    String password();
    String repeatedPassword();

    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        PasswordsValidation[] value();
    }

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
