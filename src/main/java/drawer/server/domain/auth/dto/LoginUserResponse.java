package drawer.server.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawer.server.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserResponse {

    @JsonProperty("accessToken")
    private String accessToken;

    private String email;

    private String userId;

    public static LoginUserResponse from(String token, User user) {
        return LoginUserResponse.builder().accessToken(token).email(user.getEmail()).userId(user.getId().toString()).build();
    }
}
