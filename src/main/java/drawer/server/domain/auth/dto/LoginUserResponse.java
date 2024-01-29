package drawer.server.domain.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserResponse {

    @JsonProperty("access-token")
    private String accessToken;

    public static LoginUserResponse from(String token) {
        return LoginUserResponse.builder().accessToken(token).build();
    }
}
