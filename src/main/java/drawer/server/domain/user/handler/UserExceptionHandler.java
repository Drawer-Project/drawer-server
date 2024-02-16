package drawer.server.domain.user.handler;

import drawer.server.common.error.ErrorResponse;
import drawer.server.domain.user.exception.UserErrorCode;
import drawer.server.domain.user.exception.UserException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ErrorResponse> handleUserException(UserException exception) {
        UserErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse =
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), errorCode.getDetail());

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
