package drawer.server.domain.bookmark.exception.bookmark;

public class BookmarkNotFoundException extends BookmarkException {

    public BookmarkNotFoundException() {
        super(BookmarkErrorCode.BOOKMARK_NOT_FOUND);
    }
}
