package drawer.server.domain.auth.controller;

import drawer.server.domain.auth.dto.LoginUserRequest;
import drawer.server.domain.auth.dto.LoginUserResponse;
import drawer.server.domain.auth.dto.SignupUserRequest;
import drawer.server.domain.auth.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    private final String BEARER_ = "Bearer ";

    @PostMapping("/api/v1/signup")
    public ResponseEntity<Void> signup(@RequestBody SignupUserRequest request) {
        authService.signup(request);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/api/v1/signout")
    public ResponseEntity<Void> signout(HttpServletRequest request) {
        authService.signout(request);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/api/v1/login")
    public ResponseEntity<LoginUserResponse> login(@RequestBody LoginUserRequest request) {
        LoginUserResponse response = authService.login(request);
        return ResponseEntity.status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, BEARER_ + response.getAccessToken())
                .body(response);
    }
}
