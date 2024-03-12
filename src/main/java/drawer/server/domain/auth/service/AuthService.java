package drawer.server.domain.auth.service;

import drawer.server.domain.auth.dto.LoginUserRequest;
import drawer.server.domain.auth.dto.LoginUserResponse;
import drawer.server.domain.auth.dto.SignupUserRequest;
import drawer.server.domain.auth.exception.AuthException;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {

    void signup(SignupUserRequest request);

    void signout(HttpServletRequest request);

    LoginUserResponse login(LoginUserRequest request) throws AuthException;
}
