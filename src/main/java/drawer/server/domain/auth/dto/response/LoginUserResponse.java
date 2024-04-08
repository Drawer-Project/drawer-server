package drawer.server.domain.auth.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import drawer.server.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserResponse {

    private String uuid;

    private String email;

    private String profileImageUrl;

    /**
     * 본 DTO 클래스의 accessToken 필드는 생성된 토큰을 controller로 넘겨주기 위함이다. response의 body에는 존재하지 않아야 하므로
     * contoller에서 이 값을 통해 직접 header의 값을 설정해주어 반환한다.
     */
    @JsonIgnore private String accessToken;

    public static LoginUserResponse from(String token, User user) {
        return LoginUserResponse.builder()
                .uuid(user.getUuid())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .accessToken(token)
                .build();
    }
}
