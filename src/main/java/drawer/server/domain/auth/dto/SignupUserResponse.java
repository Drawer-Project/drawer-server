package drawer.server.domain.auth.dto;

import drawer.server.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupUserResponse {

    private long id;

    private String email;

    public static SignupUserResponse from(User user) {
        return SignupUserResponse.builder().id(user.getId()).email(user.getEmail()).build();
    }
}
