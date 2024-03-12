package drawer.server.domain.user.service;

import drawer.server.domain.user.dto.*;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    ReadUserInfoResponse readUser(HttpServletRequest request);

    UpdateUserProfileImageResponse updateUserProfile(String userId, MultipartFile file)
            throws IOException;

    UpdateUserEmailResponse updateUserEmail(String userId, UpdateUserEmailRequest request);
}
