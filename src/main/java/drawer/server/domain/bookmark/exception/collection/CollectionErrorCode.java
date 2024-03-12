package drawer.server.domain.bookmark.exception.collection;

import drawer.server.common.error.ErrorCode;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum CollectionErrorCode implements ErrorCode {
    COLLECTION_NOT_FOUND(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Collection does not exist."),

    USER_NOT_FOUND_IN_COLLECTION_DOMAIN(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "User not found in a collection domain."),

    BOOKMARK_NOT_FOUND_IN_COLLECTION_DOMAIN(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "Bookmark not found in a collection domain."),

    BOOKMARK_COLLECTION_NOT_FOUND_IN_COLLECTION_DOMAIN(
            HttpStatus.BAD_REQUEST.value(),
            HttpStatus.BAD_REQUEST.getReasonPhrase(),
            "BookmarkCollection not found in a collection domain.");

    private final int status;

    private final String message;

    private final String detail;

    CollectionErrorCode(int status, String message, String detail) {
        this.status = status;
        this.message = message;
        this.detail = detail;
    }
}
