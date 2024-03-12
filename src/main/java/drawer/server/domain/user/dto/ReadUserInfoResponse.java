package drawer.server.domain.user.dto;

import drawer.server.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserInfoResponse {

    private String uuid;

    private String email;

    private String profileImageUrl;

    public static ReadUserInfoResponse from(User user) {
        return ReadUserInfoResponse.builder()
                .uuid(user.getUuid())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
