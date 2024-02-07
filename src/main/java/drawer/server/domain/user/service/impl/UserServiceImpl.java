package drawer.server.domain.user.service.impl;

import drawer.server.domain.user.dto.ReadUserInfoResponse;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.exception.UserErrorCode;
import drawer.server.domain.user.exception.UserNotFoundException;
import drawer.server.domain.user.repository.UserRepository;
import drawer.server.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ReadUserInfoResponse readUser(long userId) {
        User foundUser = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException(UserErrorCode.USER_NOT_FOUND));

        return ReadUserInfoResponse.from(foundUser);
    }
}
