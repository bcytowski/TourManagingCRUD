package pl.sda.javagda28.tourmanagingcrud.exceptions;

public class NotEmptyException extends RuntimeException {

    public NotEmptyException() {
        super();
    }

    public NotEmptyException(final String message) {
        super(message);
    }

    public NotEmptyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public NotEmptyException(final Throwable cause) {
        super(cause);
    }

    protected NotEmptyException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
