// package drawer.server.domain.post.controller;
//
// import drawer.server.domain.post.dto.collection.*;
// import drawer.server.domain.post.service.CollectionService;
// import lombok.RequiredArgsConstructor;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.*;
//
// @RestController
// @RequiredArgsConstructor
// public class CollectionController {
//
//    private final CollectionService collectionService;
//
//    @PostMapping("/api/collections")
//    public ResponseEntity<Void> createCollection(@RequestBody AddCollectionRequest request) {
//        collectionService.create(request);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }
//
////        @GetMapping("/api/collections/{userId}")
////        public ResponseEntity<GetAllCollectionResponse> getAllCollection(@PathVariable("userId")
////     Long userId) {
////            GetAllCollectionResponse collections = collectionService.readAllByUser(userId);
////            return ResponseEntity.ok(collections);
////        }
//
//    @PutMapping("/api/collections/update")
//    public ResponseEntity<UpdateCollectionResponse> updateCollection(
//            @RequestBody UpdateCollectionRequest request) {
//        UpdateCollectionResponse updatedCollection = collectionService.update(request);
//        return ResponseEntity.ok(updatedCollection);
//    }
//
////    @DeleteMapping("/api/collections/delete")
////    public ResponseEntity<Void> deleteCollection(@RequestBody DeleteCollectionRequest request) {
////        collectionService.delete(request);
////        return ResponseEntity.ok().build();
////    }
// }
