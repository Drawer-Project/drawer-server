package drawer.server.domain.bookmark.service;

import drawer.server.domain.bookmark.dto.bookmark.*;

public interface BookmarkService {

    CreateBookmarkResponse createBookmark(String userId, CreateBookmarkRequest request);

    UpdateBookmarkResponse updateBookmark(
            String userId, String bookmarkId, UpdateBookmarkRequest request);

    void deleteBookmark(String userId, String bookmarkId);

    ReadBookmarkResponse readBookmark(String userId, String bookmarkId);

    ReadBookmarksResponse readBookmarks(String userId);
}
