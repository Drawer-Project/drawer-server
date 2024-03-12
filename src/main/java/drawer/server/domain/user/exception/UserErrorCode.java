package drawer.server.domain.user.exception;

import drawer.server.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "User does not exist.");

    private final int status;

    private final String message;

    private final String detail;

    UserErrorCode(int status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
