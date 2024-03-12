package drawer.server.domain.user.dto;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserEmailRequest {

    private String email;
}
