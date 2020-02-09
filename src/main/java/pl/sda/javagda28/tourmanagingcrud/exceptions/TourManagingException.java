package pl.sda.javagda28.tourmanagingcrud.exceptions;

public class TourManagingException extends RuntimeException {
    public TourManagingException() {
        super();
    }

    public TourManagingException(final String message) {
        super(message);
    }

    public TourManagingException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TourManagingException(final Throwable cause) {
        super(cause);
    }

    protected TourManagingException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
