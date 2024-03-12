package drawer.server.domain.bookmark.exception.bookmark;

public class UserNotFoundInBookmarkDomainException extends BookmarkException {

    public UserNotFoundInBookmarkDomainException() {
        super(BookmarkErrorCode.USER_NOT_FOUND_IN_BOOKMARK_DOMAIN);
    }
}
