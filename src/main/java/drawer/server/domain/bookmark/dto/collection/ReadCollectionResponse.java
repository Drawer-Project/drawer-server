package drawer.server.domain.bookmark.dto.collection;

import drawer.server.domain.bookmark.dto.bookmark.ReadBookmarkResponse;
import drawer.server.domain.bookmark.entity.Collection;
import java.util.List;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadCollectionResponse {

    private String collectionId;

    private String name;

    private String description;

    private List<ReadBookmarkResponse> bookmarks;

    public static ReadCollectionResponse from(
            Collection collection, List<ReadBookmarkResponse> bookmarks) {
        return ReadCollectionResponse.builder()
                .collectionId(collection.getUuid())
                .name(collection.getName())
                .description(collection.getDescription())
                .bookmarks(bookmarks)
                .build();
    }
}
