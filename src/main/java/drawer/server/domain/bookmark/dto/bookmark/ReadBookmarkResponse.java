package drawer.server.domain.bookmark.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import drawer.server.domain.bookmark.entity.Bookmark;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadBookmarkResponse {

    private String url;

    private String title;

    @JsonProperty("bookmark_id")
    private long id;

    public static ReadBookmarkResponse from(Bookmark bookmark) {
        return ReadBookmarkResponse.builder()
                .url(bookmark.getUrl())
                .title(bookmark.getTitle())
                .id(bookmark.getId())
                .build();
    }
}
