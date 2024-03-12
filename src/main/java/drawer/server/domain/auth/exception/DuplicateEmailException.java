package drawer.server.domain.auth.exception;

public class DuplicateEmailException extends AuthException {

    public DuplicateEmailException() {
        super(AuthErrorCode.DUPLICATE_EMAIL_ERROR);
    }
}
