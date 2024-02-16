package drawer.server.domain.bookmark.service.impl;

import drawer.server.domain.bookmark.dto.bookmark.*;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.BookmarkCollection;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.exception.BookmarkErrorCode;
import drawer.server.domain.bookmark.exception.BookmarkNotFoundException;
import drawer.server.domain.bookmark.exception.UserNotFoundInBookmarkDomainException;
import drawer.server.domain.bookmark.repository.BookmarkCollectionRepository;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.repository.CollectionRepository;
import drawer.server.domain.bookmark.service.BookmarkService;
import drawer.server.domain.user.entity.User;
import drawer.server.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BookmarkServiceImpl implements BookmarkService {

    private final UserRepository userRepository;

    private final BookmarkRepository bookmarkRepository;

    private final CollectionRepository collectionRepository;

    private final BookmarkCollectionRepository bookmarkCollectionRepository;

    @Override
    @Transactional
    public CreateBookmarkResponse createBookmark(long userId, CreateBookmarkRequest request) {
        User user =
                userRepository
                        .findById(userId)
                        .orElseThrow(
                                () ->
                                        new UserNotFoundInBookmarkDomainException(
                                                BookmarkErrorCode.USER_NOT_FOUND_IN_BOOKMARK_DOMAIN));

        Collection collectionOrNull = getCollectionOrNull(request);

        Bookmark bookmark = Bookmark.of(request.getUrl(), request.getTitle(), user);

        /**
         * 북마크가 생성될 수 있는 경우는 다음과 같이 2가지가 존재한다.
         *
         * <p>1. 유저가 단순히 북마크만 생성할 때 2. 유저가 북마크를 생성하고 특정한 콜렉션에 저장할 때
         *
         * <p>유저가 단순히 북마크만 저장하는 상황은 Collection에 관한 정보가 필요 없다. 따라서 Colletion 객체 값에 null을 반환하고 반대의 경우에는
         * Collection 엔티티의 값을 사용한다.
         */
        if (collectionOrNull != null) {
            BookmarkCollection bookmarkCollection = BookmarkCollection.of(bookmark, collectionOrNull);
            bookmarkCollectionRepository.save(bookmarkCollection);
            bookmark.addToCollection(bookmarkCollection);
        }

        Bookmark savedBookmark = bookmarkRepository.save(bookmark);

        return CreateBookmarkResponse.from(savedBookmark, collectionOrNull);
    }

    @Override
    @Transactional
    public UpdateBookmarkResponse updateBookmark(
            long userId, long bookmarkId, UpdateBookmarkRequest request) {
        Bookmark bookmark = getBookmark(userId, bookmarkId);
        bookmark.update(request.getUrl(), request.getTitle());
        return UpdateBookmarkResponse.from(bookmark);
    }

    @Override
    @Transactional
    public void deleteBookmark(long userId, long bookmarkId) {
        Bookmark bookmark = getBookmark(userId, bookmarkId);
        bookmark.delete();
    }

    @Override
    public ReadBookmarkResponse readBookmark(long userId, long bookmarkId) {
        Bookmark bookmark = getBookmark(userId, bookmarkId);
        return ReadBookmarkResponse.from(bookmark);
    }

    @Override
    public ReadBookmarksResponse readBookmarks(long userId) {
        return ReadBookmarksResponse.from(bookmarkRepository.findAllByUserId(userId).stream()
                .map(ReadBookmarkResponse::from)
                .toList());
    }

    private Collection getCollectionOrNull(CreateBookmarkRequest request) {
        return request.hasCollectionInfo()
                ? collectionRepository.findById(request.getId()).orElse(null)
                : null;
    }

    private Bookmark getBookmark(long userId, long bookmarkId) {
        return bookmarkRepository
                .findByIdAndUserId(userId, bookmarkId)
                .orElseThrow(() -> new BookmarkNotFoundException(BookmarkErrorCode.BOOKMARK_NOT_FOUND));
    }
}
