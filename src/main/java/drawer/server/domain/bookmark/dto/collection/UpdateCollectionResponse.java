package drawer.server.domain.bookmark.dto.collection;

import drawer.server.domain.bookmark.entity.Collection;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UpdateCollectionResponse {

    private String name;

    private String description;

    public static UpdateCollectionResponse from(Collection collection) {
        return UpdateCollectionResponse.builder()
                .name(collection.getName())
                .description(collection.getDescription())
                .build();
    }
}
