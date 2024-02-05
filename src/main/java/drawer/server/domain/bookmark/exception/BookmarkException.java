package drawer.server.domain.bookmark.exception;

import lombok.Getter;

@Getter
public class BookmarkException extends RuntimeException {

    private final BookmarkErrorCode errorCode;

    public BookmarkException(BookmarkErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
