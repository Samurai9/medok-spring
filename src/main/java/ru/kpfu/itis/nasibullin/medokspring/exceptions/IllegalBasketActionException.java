package ru.kpfu.itis.nasibullin.medokspring.exceptions;

public class IllegalBasketActionException extends RuntimeException {
    public IllegalBasketActionException() {
        super();
    }

    public IllegalBasketActionException(String message) {
        super(message);
    }

    public IllegalBasketActionException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalBasketActionException(Throwable cause) {
        super(cause);
    }

    protected IllegalBasketActionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
