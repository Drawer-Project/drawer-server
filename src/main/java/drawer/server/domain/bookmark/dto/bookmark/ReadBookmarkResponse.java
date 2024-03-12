package drawer.server.domain.bookmark.dto.bookmark;

import drawer.server.domain.bookmark.entity.Bookmark;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadBookmarkResponse {

    private String bookmarkId;

    private String url;

    private String title;

    public static ReadBookmarkResponse from(Bookmark bookmark) {
        return ReadBookmarkResponse.builder()
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .bookmarkId(bookmark.getUuid())
                .build();
    }
}
