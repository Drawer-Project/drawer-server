package drawer.server.domain.bookmark.dto.collection;

import java.util.List;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadCollectionsResponse {

    private List<Collection> collections;

    @Getter
    @Builder
    @ToString
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Collection {

        private String collectionId;

        private String name;

        private String description;

        public static Collection from(drawer.server.domain.bookmark.entity.Collection collection) {
            return Collection.builder()
                    .collectionId(collection.getUuid())
                    .name(collection.getName())
                    .description(collection.getDescription())
                    .build();
        }
    }

    public static ReadCollectionsResponse from(
            List<drawer.server.domain.bookmark.entity.Collection> collections) {
        return ReadCollectionsResponse.builder()
                .collections(collections.stream().map((Collection::from)).toList())
                .build();
    }
}
