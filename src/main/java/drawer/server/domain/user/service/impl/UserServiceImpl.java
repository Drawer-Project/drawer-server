package drawer.server.domain.user.service.impl;

import drawer.server.security.SecurityUtils;
import drawer.server.security.jwt.JwtTokenManager;
import drawer.server.domain.user.dto.*;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.exception.UserNotFoundException;
import drawer.server.domain.user.repository.UserRepository;
import drawer.server.domain.user.service.UserService;
import drawer.server.lib.StorageService;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final JwtTokenManager jwtTokenManager;

    private final StorageService storageService;

    @Override
    public ReadUserInfoResponse readUser(HttpServletRequest request) {
        String token = SecurityUtils.resolveBearerToken(request);
        User user = getUserByEmail(jwtTokenManager.getUsername(token));

        return ReadUserInfoResponse.from(user);
    }

    @Override
    @Transactional
    public UpdateUserProfileImageResponse updateUserProfile(String userId, MultipartFile file)
            throws IOException {
        User user = getUserByUuid(userId);
        storageService.uploadFile(userId, file);
        user.updateProfileImageUrl(storageService.getFileUrl(userId));

        return UpdateUserProfileImageResponse.from(user);
    }

    @Override
    @Transactional
    public UpdateUserEmailResponse updateUserEmail(String userId, UpdateUserEmailRequest request) {
        User user = getUserByUuid(userId);
        user.updateEmail(request.getEmail());

        return UpdateUserEmailResponse.from(user);
    }

    private User getUserByUuid(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new);
    }

    private User getUserByEmail(String username) {
        return userRepository.findByEmail(username).orElseThrow(UserNotFoundException::new);
    }
}
