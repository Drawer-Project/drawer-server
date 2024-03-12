package drawer.server.domain.user.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserProfileUImageRequest {

    private MultipartFile profileImage;
}
