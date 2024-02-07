package drawer.server.domain.user.controller;

import drawer.server.domain.user.dto.ReadUserInfoResponse;
import drawer.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/users/{userId}")
    public ResponseEntity<ReadUserInfoResponse> readUser(@PathVariable("userId") long userId) {
        ReadUserInfoResponse readUserInfoResponse = userService.readUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(readUserInfoResponse);
    }
}
