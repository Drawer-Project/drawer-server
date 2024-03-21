package drawer.server.domain.bookmark.dto.bookmark;

import drawer.server.domain.bookmark.entity.Bookmark;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateBookmarkResponse {

    private String bookmarkId;

    private String url;

    private String title;

    public static CreateBookmarkResponse from(Bookmark bookmark) {
        return CreateBookmarkResponse.builder()
                .bookmarkId(bookmark.getUuid())
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .build();
    }
}
