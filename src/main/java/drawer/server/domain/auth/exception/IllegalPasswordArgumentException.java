package drawer.server.domain.auth.exception;

public class IllegalPasswordArgumentException extends AuthException {

    public IllegalPasswordArgumentException() {
        super(AuthErrorCode.PASSWORD_DOES_NOT_MATCHED_ERROR);
    }
}
