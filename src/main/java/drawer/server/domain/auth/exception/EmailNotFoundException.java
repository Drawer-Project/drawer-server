package drawer.server.domain.auth.exception;

public class EmailNotFoundException extends AuthException {

    public EmailNotFoundException() {
        super(AuthErrorCode.EMAIL_DOES_NOT_EXIST_ERROR);
    }
}
