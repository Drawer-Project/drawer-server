package drawer.server.domain.bookmark.dto.bookmark;

import drawer.server.domain.bookmark.entity.Bookmark;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookmarkResponse {

    private String bookmarkId;

    private String url;

    private String title;

    public static UpdateBookmarkResponse from(Bookmark bookmark) {
        return UpdateBookmarkResponse.builder()
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .bookmarkId(bookmark.getUuid())
                .build();
    }
}
