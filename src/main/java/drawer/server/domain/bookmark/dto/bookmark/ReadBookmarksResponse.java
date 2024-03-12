package drawer.server.domain.bookmark.dto.bookmark;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ReadBookmarksResponse {

    @JsonProperty("bookmarks")
    private List<ReadBookmarkResponse> bookmarkResponses;

    public static ReadBookmarksResponse from(List<ReadBookmarkResponse> bookmarkResponses) {
        return ReadBookmarksResponse.builder().bookmarkResponses(bookmarkResponses).build();
    }
}
