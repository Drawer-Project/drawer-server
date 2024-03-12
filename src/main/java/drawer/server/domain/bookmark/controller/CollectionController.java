package drawer.server.domain.bookmark.controller;

import drawer.server.domain.bookmark.dto.collection.*;
import drawer.server.domain.bookmark.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class CollectionController {

    private final CollectionService collectionService;

    @PostMapping("/api/v1/users/{userId}/collections")
    public ResponseEntity<CreateCollectionResponse> createCollection(
            @PathVariable("userId") String userId, @RequestBody CreateCollectionRequest request) {
        CreateCollectionResponse response = collectionService.createCollection(userId, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("api/v1/users/{userId}/collections/{collectionId}")
    public ResponseEntity<UpdateCollectionResponse> updateCollection(
            @PathVariable("userId") String userId,
            @PathVariable("collectionId") String collectionId,
            @RequestBody UpdateCollectionRequest request) {
        UpdateCollectionResponse response =
                collectionService.updateCollection(userId, collectionId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("api/v1/users/{userId}/collections/{collectionId}")
    public ResponseEntity<Void> deleteCollection(
            @PathVariable("userId") String userId, @PathVariable("collectionId") String collectionId) {
        collectionService.deleteCollection(userId, collectionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("api/v1/users/{userId}/collections")
    public ResponseEntity<ReadCollectionsResponse> readCollections(
            @PathVariable("userId") String userId) {
        ReadCollectionsResponse response = collectionService.readCollections(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("api/v1/users/{userId}/collections/{collectionId}/bookmarks")
    public ResponseEntity<ReadCollectionResponse> readCollection(
            @PathVariable("userId") String userId, @PathVariable("collectionId") String collectionId) {
        ReadCollectionResponse response = collectionService.readCollection(userId, collectionId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("api/v1/users/{userId}/collections/{collectionId}/bookmarks")
    public ResponseEntity<AddBookmarkToCollectionResponse> addBookmarkToCollection(
            @PathVariable("userId") String userId,
            @PathVariable("collectionId") String collectionId,
            @RequestBody AddBookmarkToCollectionRequest request) {
        AddBookmarkToCollectionResponse response =
                collectionService.addBookmarkToCollection(userId, collectionId, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("api/v1/users/{userId}/collections/{collectionId}/bookmarks/{bookmarkId}")
    public ResponseEntity<Void> removeBookmarkFromCollection(
            @PathVariable("userId") String userId,
            @PathVariable("collectionId") String collectionId,
            @PathVariable("bookmarkId") String bookmarkId) {
        collectionService.removeBookmarkFromCollection(userId, bookmarkId, collectionId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
