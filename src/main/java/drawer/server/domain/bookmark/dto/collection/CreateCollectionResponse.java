package drawer.server.domain.bookmark.dto.collection;

import drawer.server.domain.bookmark.entity.Collection;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectionResponse {

    private String collectionId;

    private String name;

    private String description;

    public static CreateCollectionResponse from(Collection collection) {
        return CreateCollectionResponse.builder()
                .collectionId(collection.getUuid())
                .name(collection.getName())
                .description(collection.getDescription())
                .build();
    }
}
