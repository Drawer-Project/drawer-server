package drawer.server.domain.bookmark.dto.collection;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class DeleteCollectionRequest {

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("collection_id")
    private Long collectionId;
}
