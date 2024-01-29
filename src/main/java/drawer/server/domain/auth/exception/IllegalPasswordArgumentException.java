package drawer.server.domain.auth.exception;

public class IllegalPasswordArgumentException extends AuthException {

    public IllegalPasswordArgumentException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
