package drawer.server.domain.verification.handler;

import drawer.server.common.error.ErrorResponse;
import drawer.server.domain.verification.exception.VerificationErrorCode;
import drawer.server.domain.verification.exception.VerificationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class VerificationExceptionHandler {

    @ExceptionHandler(VerificationException.class)
    public ResponseEntity<ErrorResponse> handleVerificationException(
            VerificationException exception) {
        VerificationErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse =
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), errorCode.getDetail());

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
