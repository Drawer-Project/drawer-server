package drawer.server.common.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(GlobalException.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(GlobalException exception) {
        ErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse =
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), errorCode.getDetail());
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
