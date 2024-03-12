package drawer.server.domain.bookmark.repository.query;

import drawer.server.domain.bookmark.entity.Bookmark;
import java.util.List;

public interface CustomBookmarkRepository {

    List<Bookmark> findAllByUserUuidAndCollectionUuid(String userId, String collectionId);
}
