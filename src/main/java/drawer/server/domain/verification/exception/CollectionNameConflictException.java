package drawer.server.domain.verification.exception;

public class CollectionNameConflictException extends VerificationException {

    public CollectionNameConflictException() {
        super(VerificationErrorCode.COLLECTION_NAME_CONFLICT);
    }
}
