package drawer.server.domain.bookmark.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookmarkRequest {

    @JsonProperty("collection_id")
    private Long id;

    private String url;

    private String title;

    public boolean hasCollectionInfo() {
        return id != null;
    }
}
