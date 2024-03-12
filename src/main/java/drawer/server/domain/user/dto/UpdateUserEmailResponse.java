package drawer.server.domain.user.dto;

import drawer.server.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserEmailResponse {

    private String email;

    private String profileImageUrl;

    public static UpdateUserEmailResponse from(User user) {
        return UpdateUserEmailResponse.builder()
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .build();
    }
}
