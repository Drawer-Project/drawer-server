package drawer.server.domain.bookmark.dto.bookmark;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateBookmarkRequest {

    private String url;

    private String title;
}
