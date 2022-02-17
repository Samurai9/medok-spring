package ru.kpfu.itis.nasibullin.medokspring.exceptions;

public class IllegalProductIdException extends RuntimeException {
    public IllegalProductIdException() {
        super();
    }

    public IllegalProductIdException(String message) {
        super(message);
    }

    public IllegalProductIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalProductIdException(Throwable cause) {
        super(cause);
    }

    protected IllegalProductIdException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
