package ru.kpfu.itis.nasibullin.medokspring.exceptions;

public class UserNotAuthorizedException extends RuntimeException {
    public UserNotAuthorizedException() {
        super();
    }

    public UserNotAuthorizedException(String message) {
        super(message);
    }

    public UserNotAuthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotAuthorizedException(Throwable cause) {
        super(cause);
    }

    protected UserNotAuthorizedException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
