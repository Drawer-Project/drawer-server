package drawer.server.domain.auth.service.impl;

import drawer.server.common.security.jwt.JwtTokenManager;
import drawer.server.domain.auth.dto.LoginUserRequest;
import drawer.server.domain.auth.dto.LoginUserResponse;
import drawer.server.domain.auth.dto.SignupUserRequest;
import drawer.server.domain.auth.exception.AuthErrorCode;
import drawer.server.domain.auth.exception.IllegalEmailArgumentException;
import drawer.server.domain.auth.exception.IllegalPasswordArgumentException;
import drawer.server.domain.auth.service.AuthService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtTokenManager jwtTokenManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupUserRequest request) {
        userRepository.save(User.of(request.getEmail(), passwordEncoder.encode(request.getPassword())));
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) throws RuntimeException {
        User user =
                userRepository
                        .findByEmail(request.getEmail())
                        .orElseThrow(
                                () -> new IllegalEmailArgumentException(AuthErrorCode.EMAIL_DOES_NOT_EXIST_ERROR));

        if (passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            return LoginUserResponse.from(
                    jwtTokenManager.createToken(user.getUsername(), List.of("users")));
        } else {
            throw new IllegalPasswordArgumentException(AuthErrorCode.PASSWORD_DOES_NOT_MATCHED_ERROR);
        }
    }
}
