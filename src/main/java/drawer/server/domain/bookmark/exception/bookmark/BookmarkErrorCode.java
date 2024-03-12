package drawer.server.domain.bookmark.exception.bookmark;

import drawer.server.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum BookmarkErrorCode implements ErrorCode {
    BOOKMARK_NOT_FOUND(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Bookmark does not exist."),

    USER_NOT_FOUND_IN_BOOKMARK_DOMAIN(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "User not found in a bookmark domain.");

    private final int status;

    private final String message;

    private final String detail;

    BookmarkErrorCode(int status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
