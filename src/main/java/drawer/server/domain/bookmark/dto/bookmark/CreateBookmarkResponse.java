package drawer.server.domain.bookmark.dto.bookmark;

import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.Collection;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateBookmarkResponse {

    private String bookmarkId;

    /** Nullable */
    private String collectionId;

    private String url;

    private String title;

    public static CreateBookmarkResponse from(Bookmark bookmark, Collection collection) {
        return CreateBookmarkResponse.builder()
                .bookmarkId(bookmark.getUuid())
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .collectionId(collection == null ? null : collection.getUuid())
                .build();
    }
}
