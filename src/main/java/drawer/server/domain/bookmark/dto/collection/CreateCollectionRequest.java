package drawer.server.domain.bookmark.dto.collection;

import lombok.*;

@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CreateCollectionRequest {

    private String name;

    private String description;
}
