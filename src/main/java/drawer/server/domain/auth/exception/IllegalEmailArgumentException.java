package drawer.server.domain.auth.exception;

public class IllegalEmailArgumentException extends AuthException {

    public IllegalEmailArgumentException(AuthErrorCode errorCode) {
        super(errorCode);
    }
}
