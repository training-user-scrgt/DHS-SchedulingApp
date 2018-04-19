package gov.dhs.uscis.odos2.inventory.exception;

public class InvalidInventoryException extends Exception {

    public InvalidInventoryException() {
        super();
    }

    public InvalidInventoryException(String message) {
        super(message);
    }

    public InvalidInventoryException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidInventoryException(Throwable cause) {
        super(cause);
    }

    protected InvalidInventoryException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
