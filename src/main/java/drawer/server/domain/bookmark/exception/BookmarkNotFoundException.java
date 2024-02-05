package drawer.server.domain.bookmark.exception;

public class BookmarkNotFoundException extends BookmarkException {

    public BookmarkNotFoundException(BookmarkErrorCode errorCode) {
        super(errorCode);
    }
}
