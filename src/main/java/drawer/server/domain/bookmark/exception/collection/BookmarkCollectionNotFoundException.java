package drawer.server.domain.bookmark.exception.collection;

public class BookmarkCollectionNotFoundException extends CollectionException {

    public BookmarkCollectionNotFoundException() {
        super(CollectionErrorCode.BOOKMARK_NOT_FOUND_IN_COLLECTION_DOMAIN);
    }
}
