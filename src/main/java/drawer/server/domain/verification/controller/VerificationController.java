package drawer.server.domain.verification.controller;

import drawer.server.domain.verification.service.impl.VerificationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class VerificationController {

    private final VerificationServiceImpl verificationService;

    @GetMapping("api/v1/users/{userId}/check-duplicate-collection-name")
    public ResponseEntity<Void> checkDuplicateCollectionName(
            @PathVariable("userId") String userId, @RequestParam("name") String name) {
        verificationService.verifyCollectionName(userId, name);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
