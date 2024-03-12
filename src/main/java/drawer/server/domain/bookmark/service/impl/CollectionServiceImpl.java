package drawer.server.domain.bookmark.service.impl;

import drawer.server.domain.bookmark.dto.bookmark.ReadBookmarkResponse;
import drawer.server.domain.bookmark.dto.collection.*;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.BookmarkCollection;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.exception.collection.*;
import drawer.server.domain.bookmark.repository.BookmarkCollectionRepository;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.repository.CollectionRepository;
import drawer.server.domain.bookmark.service.CollectionService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CollectionServiceImpl implements CollectionService {

    private final UserRepository userRepository;

    private final BookmarkRepository bookmarkRepository;

    private final CollectionRepository collectionRepository;

    private final BookmarkCollectionRepository bookmarkCollectionRepository;

    @Override
    @Transactional
    public CreateCollectionResponse createCollection(String uuid, CreateCollectionRequest request) {
        User user = getUser(uuid);
        Collection savedCollection = saveCollection(request, user);

        return CreateCollectionResponse.from(savedCollection);
    }

    @Override
    @Transactional
    public UpdateCollectionResponse updateCollection(
            String userId, String collectionId, UpdateCollectionRequest request) {
        Collection collection = getCollection(userId, collectionId);
        collection.update(request.getName(), request.getDescription());

        return UpdateCollectionResponse.from(collection);
    }

    @Override
    @Transactional
    public void deleteCollection(String userId, String collectionId) {
        Collection collection = getCollection(userId, collectionId);
        collection.delete();
    }

    @Override
    public ReadCollectionResponse readCollection(String userId, String collectionId) {
        User user = getUser(userId);
        Collection collection = getCollection(collectionId);

        List<ReadBookmarkResponse> bookmarks =
                bookmarkRepository.findAllByUserUuidAndCollectionUuid(user.getUuid(), collectionId).stream()
                        .map(ReadBookmarkResponse::from)
                        .toList();

        return ReadCollectionResponse.from(collection, bookmarks);
    }

    @Override
    public ReadCollectionsResponse readCollections(String userId) {
        List<Collection> collections = getCollections(userId);

        return ReadCollectionsResponse.from(collections);
    }

    @Override
    @Transactional
    public AddBookmarkToCollectionResponse addBookmarkToCollection(
            String userId, String collectionId, AddBookmarkToCollectionRequest request) {
        Collection collection = getCollection(userId, collectionId);
        Bookmark bookmark = getBookmark(request);
        createRelationship(collection, bookmark);

        return AddBookmarkToCollectionResponse.from(collection, bookmark);
    }

    @Override
    @Transactional
    public void removeBookmarkFromCollection(String userId, String bookmarkId, String collectionId) {
        BookmarkCollection bookmarkCollection = getRelationship(bookmarkId, collectionId);
        bookmarkCollection.delete();
        bookmarkCollectionRepository.deleteById(bookmarkCollection.getId());
    }

    private Collection saveCollection(CreateCollectionRequest request, User user) {
        Collection collection = Collection.of(request.getName(), request.getDescription(), user);
        Collection savedCollection = collectionRepository.save(collection);
        return savedCollection;
    }

    private void createRelationship(Collection collection, Bookmark bookmark) {
        BookmarkCollection bookmarkCollection = BookmarkCollection.of(bookmark, collection);
        bookmarkCollectionRepository.save(bookmarkCollection);
    }

    private Collection getCollection(String collectionId) {
        return collectionRepository
                .findByUuid(collectionId)
                .orElseThrow(CollectionNotFoundException::new);
    }

    private Collection getCollection(String userId, String collectionId) {
        return collectionRepository
                .findByUuidAndUserUuid(collectionId, userId)
                .orElseThrow(CollectionNotFoundException::new);
    }

    private List<Collection> getCollections(String userId) {
        return collectionRepository.findByUserUuid(userId);
    }

    private User getUser(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(UserNotFoundException::new);
    }

    private Bookmark getBookmark(AddBookmarkToCollectionRequest request) {
        return bookmarkRepository
                .findByUuid(request.getBookmarkId())
                .orElseThrow(BookmarkNotFoundException::new);
    }

    private BookmarkCollection getRelationship(String bookmarkId, String collectionId) {
        return bookmarkCollectionRepository
                .findByBookmarkUuidAndCollectionUuid(bookmarkId, collectionId)
                .orElseThrow(BookmarkCollectionNotFoundException::new);
    }
}
