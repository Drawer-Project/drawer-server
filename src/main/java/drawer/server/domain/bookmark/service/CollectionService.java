package drawer.server.domain.bookmark.service;

import drawer.server.domain.bookmark.dto.collection.*;

public interface CollectionService {

    CreateCollectionResponse createCollection(String userId, CreateCollectionRequest request);

    UpdateCollectionResponse updateCollection(
            String userId, String collectionId, UpdateCollectionRequest request);

    void deleteCollection(String userId, String collectionId);

    ReadCollectionResponse readCollection(String userId, String collectionId);

    ReadCollectionsResponse readCollections(String userId);

    AddBookmarkToCollectionResponse addBookmarkToCollection(
            String userId, String collectionId, AddBookmarkToCollectionRequest request);

    void removeBookmarkFromCollection(String userId, String bookmarkId, String collectionId);
}
