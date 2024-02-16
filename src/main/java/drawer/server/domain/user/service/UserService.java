package drawer.server.domain.user.service;

import drawer.server.domain.bookmark.dto.bookmark.*;
import drawer.server.domain.user.dto.ReadUserInfoResponse;

public interface UserService {

    ReadUserInfoResponse readUser(long userId);
}
