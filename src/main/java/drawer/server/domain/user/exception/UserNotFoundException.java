package drawer.server.domain.user.exception;

public class UserNotFoundException extends UserException {

    public UserNotFoundException(UserErrorCode errorCode) {
        super(errorCode);
    }
}
