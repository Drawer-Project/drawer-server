package drawer.server.domain.auth.service;

import drawer.server.domain.auth.dto.LoginUserRequest;
import drawer.server.domain.auth.dto.LoginUserResponse;
import drawer.server.domain.auth.dto.SignupUserRequest;

public interface AuthService {

    void signup(SignupUserRequest request);

    LoginUserResponse login(LoginUserRequest request) throws RuntimeException;
}
