package drawer.server.domain.verification.exception;

import lombok.Getter;

@Getter
public class VerificationException extends RuntimeException {

    private final VerificationErrorCode errorCode;

    public VerificationException(VerificationErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
