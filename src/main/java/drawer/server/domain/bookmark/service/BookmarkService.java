package drawer.server.domain.bookmark.service;

import drawer.server.domain.bookmark.dto.bookmark.*;
import java.util.List;

public interface BookmarkService {

    CreateBookmarkResponse createBookmark(long userId, CreateBookmarkRequest request);

    UpdateBookmarkResponse updateBookmark(
            long userId, long bookmarkId, UpdateBookmarkRequest request);

    void deleteBookmark(long userId, long bookmarkId);

    ReadBookmarkResponse readBookmark(long userId, long bookmarkId);

    List<ReadBookmarkResponse> readBookmarks(long userId);
}
