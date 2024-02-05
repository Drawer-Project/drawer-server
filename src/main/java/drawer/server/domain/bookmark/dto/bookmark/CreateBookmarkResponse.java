package drawer.server.domain.bookmark.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.Collection;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookmarkResponse {

    private long id;

    private String url;

    private String title;

    @JsonProperty("collection_info")
    private CollectionInfo collectionInfo;

    public static CreateBookmarkResponse from(Bookmark bookmark, Collection collection) {
        return CreateBookmarkResponse.builder()
                .id(bookmark.getId())
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .collectionInfo(CollectionInfo.from(collection))
                .build();
    }

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    static class CollectionInfo {

        private long id;

        private String name;

        private String description;

        public static CollectionInfo from(Collection collection) {
            if (collection == null) {
                return null;
            }

            return CollectionInfo.builder()
                    .id(collection.getId())
                    .name(collection.getName())
                    .description(collection.getDescription())
                    .build();
        }
    }
}
