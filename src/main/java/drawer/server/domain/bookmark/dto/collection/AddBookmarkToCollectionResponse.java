package drawer.server.domain.bookmark.dto.collection;

import drawer.server.domain.bookmark.dto.bookmark.ReadBookmarkResponse;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.Collection;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddBookmarkToCollectionResponse {

    private String collectionId;

    private String name;

    private String description;

    private ReadBookmarkResponse bookmark;

    public static AddBookmarkToCollectionResponse from(Collection collection, Bookmark bookmark) {
        return AddBookmarkToCollectionResponse.builder()
                .collectionId(collection.getUuid())
                .name(collection.getName())
                .description(collection.getDescription())
                .bookmark(ReadBookmarkResponse.from(bookmark))
                .build();
    }
}
