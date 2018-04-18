package gov.dhs.uscis.odos2.reservation.exception;

public class InvalidReservationException extends Exception {

    public InvalidReservationException() {
        super();
    }

    public InvalidReservationException(String message) {
        super(message);
    }

    public InvalidReservationException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidReservationException(Throwable cause) {
        super(cause);
    }

    protected InvalidReservationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
