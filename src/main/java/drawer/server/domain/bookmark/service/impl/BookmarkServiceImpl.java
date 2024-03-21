package drawer.server.domain.bookmark.service.impl;

import drawer.server.domain.bookmark.dto.bookmark.*;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.exception.bookmark.BookmarkNotFoundException;
import drawer.server.domain.bookmark.exception.bookmark.UserNotFoundInBookmarkDomainException;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.service.BookmarkService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

    private final UserRepository userRepository;

    private final BookmarkRepository bookmarkRepository;

    @Override
    @Transactional
    public CreateBookmarkResponse createBookmark(String userId, CreateBookmarkRequest request) {
        User user = getUser(userId);
        Bookmark bookmark = Bookmark.of(request.getUrl(), request.getTitle(), user);
        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return CreateBookmarkResponse.from(savedBookmark);
    }

    @Override
    @Transactional
    public UpdateBookmarkResponse updateBookmark(
            String userId, String bookmarkId, UpdateBookmarkRequest request) {
        Bookmark bookmark = getBookmark(userId, bookmarkId);
        bookmark.update(request.getUrl(), request.getTitle());

        return UpdateBookmarkResponse.from(bookmark);
    }

    @Override
    @Transactional
    public void deleteBookmark(String userId, String bookmarkId) {
        Bookmark bookmark = getBookmark(userId, bookmarkId);
        bookmark.delete();
    }

    @Override
    public ReadBookmarkResponse readBookmark(String userId, String bookmarkId) {
        Bookmark bookmark = getBookmark(userId, bookmarkId);

        return ReadBookmarkResponse.from(bookmark);
    }

    @Override
    public ReadBookmarksResponse readBookmarks(String userId) {
        return ReadBookmarksResponse.from(
                bookmarkRepository.findAllByUserUuid(userId).stream()
                        .map(ReadBookmarkResponse::from)
                        .toList());
    }

    private Bookmark getBookmark(String uuid, String bookmarkId) {
        return bookmarkRepository
                .findByUuidAndUserUuid(bookmarkId, uuid)
                .orElseThrow(BookmarkNotFoundException::new);
    }

    private User getUser(String uuid) {
        return userRepository.findByUuid(uuid).orElseThrow(UserNotFoundInBookmarkDomainException::new);
    }
}
