package drawer.server.domain.bookmark.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkRequest;
import drawer.server.domain.bookmark.dto.collection.*;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.repository.BookmarkCollectionRepository;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.repository.CollectionRepository;
import drawer.server.domain.bookmark.service.BookmarkService;
import drawer.server.domain.bookmark.service.CollectionService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class CollectionServiceImplTest {

    @Autowired private UserRepository userRepository;

    @Autowired private BookmarkRepository bookmarkRepository;

    @Autowired private CollectionRepository collectionRepository;

    @Autowired private BookmarkCollectionRepository bookmarkCollectionRepository;

    @Autowired private BookmarkService bookmarkService;

    @Autowired private CollectionService collectionService;

    private static final String TEST_EMAIL = "test@test.com";

    private static final String TEST_PASSWORD = "1234";

    private static final String TEST_URL = "www.example.com";

    private static final String TEST_TITLE = "title";

    private static final String TEST_NAME = "collection";

    private static final String TEST_DESC = "description";

    @Test
    public void testCreateCollection() {
        // given
        User savedUser = createUser();
        CreateCollectionRequest createBookmarkDto =
                CreateCollectionRequest.builder().name(TEST_NAME).description(TEST_DESC).build();

        // when
        CreateCollectionResponse createCollectionResponse =
                collectionService.createCollection(savedUser.getUuid(), createBookmarkDto);

        // then
        assertThat(createCollectionResponse.getName()).isEqualTo(TEST_NAME);
        assertThat(createCollectionResponse.getDescription()).isEqualTo(TEST_DESC);
    }

    @Test
    public void testReadCollection() {
        int iteration = 2;
        User user = createUser();
        Collection collection = createCollection(TEST_NAME, TEST_DESC, user);

        Bookmark bookmark1 = createBookmark("bookmark_url_1", "bookmark_title_1", user);
        Bookmark bookmark2 = createBookmark("bookmark_url_2", "bookmark_title_2", user);

        AddBookmarkToCollectionRequest request1 =
                AddBookmarkToCollectionRequest.builder()
                        .bookmarkId(bookmark1.getUuid())
                        .title(bookmark1.getTitle())
                        .url(bookmark1.getTitle())
                        .build();

        collectionService.addBookmarkToCollection(user.getUuid(), collection.getUuid(), request1);

        AddBookmarkToCollectionRequest request2 =
                AddBookmarkToCollectionRequest.builder()
                        .bookmarkId(bookmark2.getUuid())
                        .title(bookmark2.getTitle())
                        .url(bookmark2.getTitle())
                        .build();

        collectionService.addBookmarkToCollection(user.getUuid(), collection.getUuid(), request2);

        ReadCollectionResponse response =
                collectionService.readCollection(user.getUuid(), collection.getUuid());

        assertThat(response.getName()).isEqualTo(TEST_NAME);
        assertThat(response.getDescription()).isEqualTo(TEST_DESC);
        assertThat(response.getBookmarks().size()).isEqualTo(iteration);
    }

    @Test
    public void testReadCollections() {
        int iteration = 4;
        User user = createUser();

        createCollections(iteration, user);

        ReadCollectionsResponse response = collectionService.readCollections(user.getUuid());

        assertThat(response.getCollections().size()).isEqualTo(iteration);
    }

    @Test
    public void testAddBookmarkToCollection() {
        // given
        User user = createUser();
        Bookmark bookmark = createBookmark(TEST_URL, TEST_TITLE, user);
        Collection collection = createCollection(TEST_NAME, TEST_DESC, user);

        // when
        AddBookmarkToCollectionRequest request =
                AddBookmarkToCollectionRequest.builder()
                        .bookmarkId(bookmark.getUuid())
                        .title(bookmark.getTitle())
                        .url(bookmark.getTitle())
                        .build();

        AddBookmarkToCollectionResponse response =
                collectionService.addBookmarkToCollection(user.getUuid(), collection.getUuid(), request);

        // then
        assertThat(response.getName()).isEqualTo(collection.getName());
        assertThat(response.getDescription()).isEqualTo(collection.getDescription());
        assertThat(response.getBookmark().getUrl()).isEqualTo(bookmark.getUrl());
        assertThat(response.getBookmark().getTitle()).isEqualTo(bookmark.getTitle());
    }

    private User createUser() {
        return userRepository.save(User.builder().email(TEST_EMAIL).password(TEST_PASSWORD).build());
    }

    public Bookmark createBookmark(String url, String title, User user) {
        return bookmarkRepository.save(Bookmark.builder().url(url).title(title).user(user).build());
    }

    private Collection createCollection(String name, String description, User user) {
        return collectionRepository.save(
                Collection.builder().name(name).description(description).user(user).build());
    }

    private void createCollections(int iteration, User user) {
        for (int i = 0; i < iteration; i++) {
            createCollection("collection_name_" + i, "collection_desc_" + i, user);
        }
    }

    private void createBookmarks(int iteration, User user) {
        for (int i = 0; i < iteration; i++) {
            createBookmark("bookmark_url" + i, "bookmark_title" + i, user);
        }
    }

    private CreateBookmarkRequest createBookmarkDtoWithCollection(String collectionId) {
        return CreateBookmarkRequest.builder()
                .url(TEST_URL)
                .title(TEST_TITLE)
                .collectionId(collectionId)
                .build();
    }
}
