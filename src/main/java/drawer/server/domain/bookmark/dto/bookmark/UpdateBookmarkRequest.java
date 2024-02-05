package drawer.server.domain.bookmark.dto.bookmark;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookmarkRequest {

    private String url;

    private String title;
}
