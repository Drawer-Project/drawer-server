package drawer.server.domain.bookmark.exception;

public class UserNotFoundInBookmarkDomainException extends BookmarkException {

    public UserNotFoundInBookmarkDomainException(BookmarkErrorCode errorCode) {
        super(errorCode);
    }
}
