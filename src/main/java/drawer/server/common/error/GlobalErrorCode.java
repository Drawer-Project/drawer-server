package drawer.server.common.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum GlobalErrorCode implements ErrorCode {

    /** Unexpected Error */
    UNEXPECTED_SERVER_ERROR(
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
            "An unexpected server error occurred."),

    /** Global Authentication Error */
    ACCESS_DENIED_ERROR(
            HttpStatus.FORBIDDEN.value(),
            HttpStatus.FORBIDDEN.getReasonPhrase(),
            "The request was denied access."),

    AUTHENTICATION_FAIL_ERROR(
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            "Authentication failed.");

    private final int status;

    private final String message;

    private final String detail;

    GlobalErrorCode(int status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
