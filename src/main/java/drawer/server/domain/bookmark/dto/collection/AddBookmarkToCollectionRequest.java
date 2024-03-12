package drawer.server.domain.bookmark.dto.collection;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AddBookmarkToCollectionRequest {

    private String bookmarkId;

    private String title;

    private String url;
}
