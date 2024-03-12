package drawer.server.domain.bookmark.exception.collection;

public class CollectionNotFoundException extends CollectionException {

    public CollectionNotFoundException() {
        super(CollectionErrorCode.COLLECTION_NOT_FOUND);
    }
}
