package drawer.server.domain.bookmark.dto.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class UpdateCollectionRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("collection_id")
    private Long collectionId;

    private String name;

    private String description;
}
