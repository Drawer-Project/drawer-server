package drawer.server.domain.auth.dto.request;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginUserRequest {

    private String email;

    private String password;
}
