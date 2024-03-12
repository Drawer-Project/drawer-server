package drawer.server.domain.bookmark.exception.collection;

public class BookmarkNotFoundException extends CollectionException {

    public BookmarkNotFoundException() {
        super(CollectionErrorCode.BOOKMARK_NOT_FOUND_IN_COLLECTION_DOMAIN);
    }
}
