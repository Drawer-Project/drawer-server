package drawer.server.domain.bookmark.exception;

public class CollectionNotFoundException extends BookmarkException {

    public CollectionNotFoundException(BookmarkErrorCode errorCode) {
        super(errorCode);
    }
}
