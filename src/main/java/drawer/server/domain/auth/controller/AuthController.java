package drawer.server.domain.auth.controller;

import drawer.server.domain.auth.dto.LoginUserRequest;
import drawer.server.domain.auth.dto.LoginUserResponse;
import drawer.server.domain.auth.dto.SignupUserRequest;
import drawer.server.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/api/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupUserRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(request));
    }
}
