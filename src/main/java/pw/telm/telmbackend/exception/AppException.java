package pw.telm.telmbackend.exception;


import org.springframework.http.HttpStatus;

/**
 * Custom exception class for representing application-specific errors returned to client.
 * Extends {@link RuntimeException} to indicate unchecked exceptions that can occur during application execution.
 */
public class AppException extends RuntimeException {

    /**
     * HTTP status associated with the exception.
     */
    private final HttpStatus status;

    /**
     * Constructs an {@code AppException} with the specified message and HTTP status.
     *
     * @param message the detail message
     * @param status  the HTTP status code associated with the exception
     */
    public AppException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    /**
     * Retrieves the HTTP status associated with the exception.
     *
     * @return the HTTP status code
     */
    public HttpStatus getStatus() {
        return status;
    }
}

