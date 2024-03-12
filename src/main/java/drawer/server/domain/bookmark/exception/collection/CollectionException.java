package drawer.server.domain.bookmark.exception.collection;

import lombok.Getter;

@Getter
public class CollectionException extends RuntimeException {

    private final CollectionErrorCode errorCode;

    public CollectionException(CollectionErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
