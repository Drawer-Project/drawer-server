package drawer.server.domain.auth.handler;

import drawer.server.common.error.ErrorCode;
import drawer.server.common.error.ErrorResponse;
import drawer.server.domain.auth.exception.AuthException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AuthExceptionHandler {

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorResponse> handleAuthException(AuthException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse =
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), errorCode.getDetail());

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
