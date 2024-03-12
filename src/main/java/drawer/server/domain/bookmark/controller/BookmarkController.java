package drawer.server.domain.bookmark.controller;

import drawer.server.domain.bookmark.dto.bookmark.*;
import drawer.server.domain.bookmark.service.BookmarkService;
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
            @PathVariable("userId") String userId, @RequestBody CreateBookmarkRequest request) {
        CreateBookmarkResponse response = bookmarkService.createBookmark(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/api/v1/users/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<UpdateBookmarkResponse> updateBookmark(
            @PathVariable("userId") String userId,
            @PathVariable("bookmarkId") String bookmarkId,
            @RequestBody UpdateBookmarkRequest request) {
        UpdateBookmarkResponse response = bookmarkService.updateBookmark(userId, bookmarkId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/api/v1/users/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<Void> deleteBookmark(
            @PathVariable("userId") String userId, @PathVariable("bookmarkId") String bookmarkId) {
        bookmarkService.deleteBookmark(userId, bookmarkId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/api/v1/users/{userId}/bookmarks")
    public ResponseEntity<ReadBookmarksResponse> readBookmarks(
            @PathVariable("userId") String userId) {
        ReadBookmarksResponse response = bookmarkService.readBookmarks(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/api/v1/users/{userId}/bookmarks/{bookmarkId}")
    public ResponseEntity<ReadBookmarkResponse> readBookmark(
            @PathVariable("userId") String userId, @PathVariable("bookmarkId") String bookmarkId) {
        ReadBookmarkResponse response = bookmarkService.readBookmark(userId, bookmarkId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
