package drawer.server.domain.bookmark.handler;

import drawer.server.common.error.ErrorResponse;
import drawer.server.domain.bookmark.exception.bookmark.BookmarkErrorCode;
import drawer.server.domain.bookmark.exception.bookmark.BookmarkException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookmarkExceptionHandler {

    @ExceptionHandler(BookmarkException.class)
    public ResponseEntity<ErrorResponse> handleBookmarkException(BookmarkException exception) {
        BookmarkErrorCode errorCode = exception.getErrorCode();
        ErrorResponse errorResponse =
                new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), errorCode.getDetail());

        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
