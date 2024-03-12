package drawer.server.domain.bookmark.exception.collection;

public class UserNotFoundException extends CollectionException {

    public UserNotFoundException() {
        super(CollectionErrorCode.USER_NOT_FOUND_IN_COLLECTION_DOMAIN);
    }
}
