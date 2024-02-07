package drawer.server.domain.bookmark.service;

import drawer.server.domain.bookmark.dto.collection.*;
import drawer.server.domain.bookmark.exception.BookmarkException;

public interface CollectionService {

    void create(AddCollectionRequest request);

    UpdateCollectionResponse update(UpdateCollectionRequest request) throws BookmarkException;

    GetAllCollectionResponse readAllByUser(long userId);

    //    void delete(DeleteCollectionRequest request);
}
