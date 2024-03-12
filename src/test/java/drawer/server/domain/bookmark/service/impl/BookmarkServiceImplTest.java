package drawer.server.domain.bookmark.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkRequest;
import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkResponse;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.repository.BookmarkCollectionRepository;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.repository.CollectionRepository;
import drawer.server.domain.bookmark.service.BookmarkService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class BookmarkServiceImplTest {

    @Autowired private UserRepository userRepository;

    @Autowired private BookmarkRepository bookmarkRepository;

    @Autowired private CollectionRepository collectionRepository;

    @Autowired private BookmarkCollectionRepository bookmarkCollectionRepository;

    @Autowired private BookmarkService bookmarkService;

    private static final String TEST_EMAIL = "test@test.com";

    private static final String TEST_PASSWORD = "1234";

    private static final String TEST_URL = "www.example.com";

    private static final String TEST_TITLE = "title";

    private static final String TEST_NAME = "collection";

    private static final String TEST_DESC = "description";

    @Test
    @DisplayName("Create bookmark without collection info")
    public void testCreateBookmarkWithoutCollection() {
        // given
        User savedUser = createUser();
        CreateBookmarkRequest addBookmarkDto =
                CreateBookmarkRequest.builder().url(TEST_URL).title(TEST_TITLE).build();

        // when
        CreateBookmarkResponse createBookmarkResponse =
                bookmarkService.createBookmark(savedUser.getUuid(), addBookmarkDto);

        // then
        assertThat(createBookmarkResponse.getUrl()).isEqualTo(TEST_URL);
        assertThat(createBookmarkResponse.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(createBookmarkResponse.getCollectionId()).isNull();
    }

    @Test
    @DisplayName("Create bookmark with collection info")
    public void testCreateBookmarkWithCollection() {
        // given
        User savedUser = createUser();
        Collection savedCollection = createCollection();
        CreateBookmarkRequest addBookmarkDto =
                createBookmarkDtoWithCollection(savedCollection.getUuid());

        // when
        CreateBookmarkResponse createBookmarkResponse =
                bookmarkService.createBookmark(savedUser.getUuid(), addBookmarkDto);

        // then
        assertThat(createBookmarkResponse.getUrl()).isEqualTo(TEST_URL);
        assertThat(createBookmarkResponse.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(createBookmarkResponse.getCollectionId()).isNotNull();
    }

    @Test
    @DisplayName("Delete bookmark with soft delete")
    public void testDeleteBookmark() {
        // given
        User savedUser = createUser();
        CreateBookmarkRequest addBookmarkDto =
                CreateBookmarkRequest.builder().url(TEST_URL).title(TEST_TITLE).build();

        CreateBookmarkResponse createdBookmark =
                bookmarkService.createBookmark(savedUser.getUuid(), addBookmarkDto);

        Bookmark bookmarkBeforeDelete =
                bookmarkRepository
                        .findByUuidAndUserUuid(createdBookmark.getBookmarkId(), savedUser.getUuid())
                        .get();

        // when
        bookmarkBeforeDelete.delete();

        // then
        Optional<Bookmark> bookmarkAfterDelete =
                bookmarkRepository.findByUuidAndUserUuid(
                        createdBookmark.getBookmarkId(), savedUser.getUuid());

        assertFalse(bookmarkAfterDelete.isPresent());
    }

    private User createUser() {
        return userRepository.save(User.builder().email(TEST_EMAIL).password(TEST_PASSWORD).build());
    }

    private Collection createCollection() {
        return collectionRepository.save(
                Collection.builder().name(TEST_NAME).description(TEST_DESC).build());
    }

    private CreateBookmarkRequest createBookmarkDtoWithCollection(String collectionId) {
        return CreateBookmarkRequest.builder()
                .url(TEST_URL)
                .title(TEST_TITLE)
                .collectionId(collectionId)
                .build();
    }
}
