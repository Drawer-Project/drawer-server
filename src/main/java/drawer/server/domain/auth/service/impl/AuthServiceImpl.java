package drawer.server.domain.auth.service.impl;

import drawer.server.common.security.SecurityUtils;
import drawer.server.common.security.jwt.JwtTokenManager;
import drawer.server.domain.auth.dto.LoginUserRequest;
import drawer.server.domain.auth.dto.LoginUserResponse;
import drawer.server.domain.auth.dto.SignupUserRequest;
import drawer.server.domain.auth.exception.DuplicateEmailException;
import drawer.server.domain.auth.exception.EmailNotFoundException;
import drawer.server.domain.auth.exception.IllegalPasswordArgumentException;
import drawer.server.domain.auth.service.AuthService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final JwtTokenManager jwtTokenManager;

    private final PasswordEncoder passwordEncoder;

    @Override
    public void signup(SignupUserRequest request) {
        if (!DoesEmailExist(request.getEmail())) {
            saveUser(request);
            return;
        }

        throw new DuplicateEmailException();
    }

    @Override
    public void signout(HttpServletRequest request) {
        String token = SecurityUtils.resolveBearerToken(request);
        User user = getUserByEmail(jwtTokenManager.getUsername(token));
        userRepository.deleteById(user.getId());
    }

    @Override
    public LoginUserResponse login(LoginUserRequest request) throws RuntimeException {
        User user = getUserByEmail(request.getEmail());
        checkPasswordMatches(request.getPassword(), user.getPassword());
        String token = jwtTokenManager.createToken(user.getUsername(), List.of("users"));

        return LoginUserResponse.from(token, user);
    }

    private void saveUser(SignupUserRequest request) {
        userRepository.save(User.of(request.getEmail(), passwordEncoder.encode(request.getPassword())));
    }

    private User getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(EmailNotFoundException::new);
    }

    private void checkPasswordMatches(String providedPassword, String storedPassword) {
        if (!passwordEncoder.matches(providedPassword, storedPassword)) {
            throw new IllegalPasswordArgumentException();
        }
    }

    private boolean DoesEmailExist(String email) {
        return userRepository.findByEmail(email).isPresent();
    }
}
