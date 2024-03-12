package drawer.server.domain.bookmark.service.impl;

import drawer.server.domain.bookmark.dto.bookmark.*;
import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.entity.BookmarkCollection;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.exception.bookmark.BookmarkNotFoundException;
import drawer.server.domain.bookmark.exception.bookmark.UserNotFoundInBookmarkDomainException;
import drawer.server.domain.bookmark.repository.BookmarkCollectionRepository;
import drawer.server.domain.bookmark.repository.BookmarkRepository;
import drawer.server.domain.bookmark.repository.CollectionRepository;
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

    private final CollectionRepository collectionRepository;

    private final BookmarkCollectionRepository bookmarkCollectionRepository;

    @Override
    @Transactional
    public CreateBookmarkResponse createBookmark(String userId, CreateBookmarkRequest request) {
        User user = getUser(userId);
        Collection collectionOrNull = getCollectionOrNull(request);
        Bookmark bookmark = Bookmark.of(request.getUrl(), request.getTitle(), user);
        /*
        북마크가 생성될 수 있는 경우는 다음과 같이 2가지가 존재한다.
        1. 유저가 단순히 북마크만 생성할 때
        2. 유저가 북마크를 생성하고 특정한 콜렉션에 저장할 때
        유저가 단순히 북마크만 저장하는 상황은 Collection에 관한 정보가 필요 없다.
        따라서 Colletion 객체 값에 null을 반환하고 반대의 경우에는 Collection 엔티티의 값을 사용한다.
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

    private Collection getCollectionOrNull(CreateBookmarkRequest request) {
        return request.hasCollectionInfo()
                ? collectionRepository.findByUuid(request.getCollectionId()).orElse(null)
                : null;
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
