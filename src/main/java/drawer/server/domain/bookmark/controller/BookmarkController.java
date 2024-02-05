package drawer.server.domain.bookmark.controller;

import drawer.server.domain.bookmark.dto.bookmark.*;
import drawer.server.domain.bookmark.service.BookmarkService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @PostMapping("/api/v1/users/{userId}/bookmarks")
    public ResponseEntity<CreateBookmarkResponse> createBookmark(
            @PathVariable("userId") long userId, @RequestBody CreateBookmarkRequest request) {
        CreateBookmarkResponse bookmark = bookmarkService.createBookmark(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(bookmark);
    }

    @PutMapping("/api/v1/users/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<UpdateBookmarkResponse> updateBookmark(
            @PathVariable("userId") long userId,
            @PathVariable("bookmarkId") long bookmarkId,
            @RequestBody UpdateBookmarkRequest request) {
        UpdateBookmarkResponse bookmark = bookmarkService.updateBookmark(userId, bookmarkId, request);
        return ResponseEntity.status(HttpStatus.OK).body(bookmark);
    }

    @DeleteMapping("/api/v1/users/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(
            @PathVariable("userId") long userId, @PathVariable("bookmarkId") long bookmarkId) {
        bookmarkService.deleteBookmark(userId, bookmarkId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/api/v1/users/{userId}/bookmarks")
    public ResponseEntity<List<ReadBookmarkResponse>> readBookmarks(
            @PathVariable("userId") long userId) {
        List<ReadBookmarkResponse> bookmarks = bookmarkService.readBookmarks(userId);
        return ResponseEntity.status(HttpStatus.OK).body(bookmarks);
    }

    @GetMapping("/api/v1/users/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<ReadBookmarkResponse> readBookmark(
            @PathVariable("userId") long userId, @PathVariable("bookmarkId") long bookmarkId) {
        ReadBookmarkResponse bookmark = bookmarkService.readBookmark(userId, bookmarkId);
        return ResponseEntity.status(HttpStatus.OK).body(bookmark);
    }
}
