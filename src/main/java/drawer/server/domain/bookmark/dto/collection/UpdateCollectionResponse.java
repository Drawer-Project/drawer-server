package drawer.server.domain.bookmark.dto.collection;

import drawer.server.domain.bookmark.entity.Collection;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateCollectionResponse {

    public String collectionId;

    public String name;

    public String description;

    public static UpdateCollectionResponse from(Collection collection) {
        return UpdateCollectionResponse.builder()
                .collectionId(collection.getUuid())
                .name(collection.getName())
                .description(collection.getDescription())
                .build();
    }
}
