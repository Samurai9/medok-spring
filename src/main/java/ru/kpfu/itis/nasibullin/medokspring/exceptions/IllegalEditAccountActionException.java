package ru.kpfu.itis.nasibullin.medokspring.exceptions;

public class IllegalEditAccountActionException extends RuntimeException {
    public IllegalEditAccountActionException() {
        super();
    }

    public IllegalEditAccountActionException(String message) {
        super(message);
    }

    public IllegalEditAccountActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalEditAccountActionException(Throwable cause) {
        super(cause);
    }

    protected IllegalEditAccountActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
