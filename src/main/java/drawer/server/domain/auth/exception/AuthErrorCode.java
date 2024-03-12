package drawer.server.domain.auth.exception;

import drawer.server.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum AuthErrorCode implements ErrorCode {
    EMAIL_DOES_NOT_EXIST_ERROR(
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            "Email does not exist."),

    PASSWORD_DOES_NOT_MATCHED_ERROR(
            HttpStatus.UNAUTHORIZED.value(),
            HttpStatus.UNAUTHORIZED.getReasonPhrase(),
            "Password does not matched."),

    DUPLICATE_EMAIL_ERROR(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_GATEWAY.getReasonPhrase(),
            "The email address provided already exists in the system.");

    private final int status;

    private final String message;

    private final String detail;

    AuthErrorCode(int status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
