package drawer.server.domain.verification.exception;

import drawer.server.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum VerificationErrorCode implements ErrorCode {
    COLLECTION_NAME_CONFLICT(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "The collection name already exists.");

    private final int status;

    private final String message;

    private final String detail;

    VerificationErrorCode(int status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
