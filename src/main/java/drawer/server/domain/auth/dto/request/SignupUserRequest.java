package drawer.server.domain.auth.dto.request;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class SignupUserRequest {

    private String email;

    private String password;
}
