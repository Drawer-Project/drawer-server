package drawer.server.domain.bookmark.repository.query;

import drawer.server.domain.bookmark.entity.Collection;
import java.util.List;

public interface CustomCollectionRepository {

    List<Collection> findAllByUserUuid(String userId);
}
