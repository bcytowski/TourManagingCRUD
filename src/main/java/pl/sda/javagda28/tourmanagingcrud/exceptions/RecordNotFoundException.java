package pl.sda.javagda28.tourmanagingcrud.exceptions;

public class RecordNotFoundException extends RuntimeException {
    public RecordNotFoundException() {
        super();
    }

    public RecordNotFoundException(final String message) {
        super(message);
    }

    public RecordNotFoundException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public RecordNotFoundException(final Throwable cause) {
        super(cause);
    }

    protected RecordNotFoundException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
