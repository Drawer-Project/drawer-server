package drawer.server.domain.auth.dto;

import lombok.Getter;

@Getter
public class SignupUserRequest {

    private String email;

    private String password;

    SignupUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
