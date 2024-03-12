package drawer.server.domain.user.controller;

import drawer.server.domain.user.dto.*;
import drawer.server.domain.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/api/v1/users")
    public ResponseEntity<ReadUserInfoResponse> readUser(HttpServletRequest request) {
        ReadUserInfoResponse response = userService.readUser(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping(
            path = "/api/v1/users/{userId}",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UpdateUserEmailResponse> updateUserEmail(
            @PathVariable("userId") String userId, @RequestBody UpdateUserEmailRequest request) {
        UpdateUserEmailResponse response = userService.updateUserEmail(userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping(
            path = "/api/v1/users/{userId}",
            consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<UpdateUserProfileImageResponse> updateUserProfile(
            @PathVariable("userId") String userId, @RequestPart(value = "file") MultipartFile file)
            throws IOException {
        UpdateUserProfileImageResponse response = userService.updateUserProfile(userId, file);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
