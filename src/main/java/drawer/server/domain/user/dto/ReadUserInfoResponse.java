package drawer.server.domain.user.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawer.server.domain.user.entity.User;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadUserInfoResponse {

    private String email;

    private long userId;

    public static ReadUserInfoResponse from (User user) {
        return ReadUserInfoResponse.builder()
                .email(user.getEmail())
                .userId(user.getId())
                .build();
    }
}
