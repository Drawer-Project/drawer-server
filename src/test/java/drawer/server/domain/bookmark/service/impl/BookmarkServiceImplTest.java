package drawer.server.domain.bookmark.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkRequest;
import drawer.server.domain.bookmark.dto.bookmark.CreateBookmarkResponse;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.BookmarkCollection;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.repository.BookmarkCollectionRepository;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.repository.CollectionRepository;
import drawer.server.domain.bookmark.service.BookmarkService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
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

    @Test
    @DisplayName("Create bookmark without collection info")
    public void testCreateBookmarkWithoutCollection() throws Exception {
        // given
        User savedUser = createUser();
        CreateBookmarkRequest addBookmarkDto =
                CreateBookmarkRequest.builder().url(TEST_URL).title(TEST_TITLE).build();

        // when
        CreateBookmarkResponse createBookmarkResponse =
                bookmarkService.createBookmark(savedUser.getId(), addBookmarkDto);

        // then
        assertThat(createBookmarkResponse.getUrl()).isEqualTo(TEST_URL);
        assertThat(createBookmarkResponse.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(createBookmarkResponse.getCollectionInfo()).isNull();
    }

    @Test
    @DisplayName("Create bookmark with collection info")
    public void testCreateBookmarkWithCollection() throws Exception {
        // given
        User savedUser = createUser();
        Collection savedCollection = createCollection();
        CreateBookmarkRequest addBookmarkDto = createBookmarkDtoWithCollection(savedCollection.getId());

        // when
        CreateBookmarkResponse createBookmarkResponse =
                bookmarkService.createBookmark(savedUser.getId(), addBookmarkDto);

        // then
        assertThat(createBookmarkResponse.getUrl()).isEqualTo(TEST_URL);
        assertThat(createBookmarkResponse.getTitle()).isEqualTo(TEST_TITLE);
        assertThat(createBookmarkResponse.getCollectionInfo()).isNotNull();
    }

    @Test
    @DisplayName("Delete bookmark with soft delete")
    public void testDeleteBookmark() throws Exception {
        // given
        User savedUser = createUser();
        CreateBookmarkRequest addBookmarkDto =
                CreateBookmarkRequest.builder()
                        .url(TEST_URL)
                        .title(TEST_TITLE)
                        .id(savedUser.getId())
                        .build();

        CreateBookmarkResponse createdBookmark =
                bookmarkService.createBookmark(savedUser.getId(), addBookmarkDto);

        Bookmark bookmarkBeforeDelete =
                bookmarkRepository.findByIdAndUserId(savedUser.getId(), createdBookmark.getId()).get();

        // when
        bookmarkBeforeDelete.delete();

        // then
        Bookmark bookmarkAfterDelete =
                bookmarkRepository.findByIdAndUserId(savedUser.getId(), createdBookmark.getId()).get();
        assertTrue(bookmarkAfterDelete.getDeleteFlag().isDeleted());
    }

    @Test
    @Rollback(value = false)
    @DisplayName("Delete bookmark with linked collection")
    public void testDeleteBookmarkWithBookmarkCollection() throws Exception {
        // given
        User savedUser = createUser();
        Collection savedCollection = createCollection();
        CreateBookmarkRequest addBookmarkDto = createBookmarkDtoWithCollection(savedCollection.getId());

        CreateBookmarkResponse createBookmarkResponse =
                bookmarkService.createBookmark(savedUser.getId(), addBookmarkDto);
        Bookmark bookmarkBeforeDelete =

                bookmarkRepository
                        .findByIdAndUserId(savedUser.getId(), createBookmarkResponse.getId())
                        .get();

        // when
        bookmarkBeforeDelete.delete();

        // then
        Bookmark bookmarkAfterDelete =
                bookmarkRepository
                        .findByIdAndUserId(savedUser.getId(), createBookmarkResponse.getId())
                        .get();

        BookmarkCollection bookmarkCollectionAfterDelete =
                bookmarkCollectionRepository.findByBookmarkId(createBookmarkResponse.getId());

        assertTrue(bookmarkAfterDelete.getDeleteFlag().isDeleted());
        assertTrue(bookmarkCollectionAfterDelete.getDeleteFlag().isDeleted());
    }

    private User createUser() {
        return userRepository.save(User.builder().email(TEST_EMAIL).password(TEST_PASSWORD).build());
    }

    private Collection createCollection() {
        return collectionRepository.save(
                Collection.builder().name("collection").description("description").build());
    }

    private CreateBookmarkRequest createBookmarkDtoWithCollection(Long collectionId) {
        return CreateBookmarkRequest.builder().url(TEST_URL).title(TEST_TITLE).id(collectionId).build();
    }
}
