package drawer.server.domain.bookmark.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawer.server.domain.bookmark.entity.Bookmark;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookmarkResponse {

    @JsonProperty("bookmark_id")
    private long id;

    private String url;

    private String title;

    public static UpdateBookmarkResponse from(Bookmark bookmark) {
        return UpdateBookmarkResponse.builder()
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .id(bookmark.getId())
                .build();
    }
}
