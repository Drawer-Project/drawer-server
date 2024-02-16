// package drawer.server.domain.bookmark.service.impl;
//
// import drawer.server.domain.bookmark.dto.collection.*;
// import drawer.server.domain.bookmark.entity.Collection;
// import drawer.server.domain.bookmark.exception.BookmarkErrorCode;
// import drawer.server.domain.bookmark.exception.BookmarkException;
// import drawer.server.domain.bookmark.exception.CollectionNotFoundException;
// import drawer.server.domain.bookmark.repository.CollectionRepository;
// import drawer.server.domain.bookmark.service.CollectionService;
// import java.util.List;
// import lombok.RequiredArgsConstructor;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
//
// @Service
// @RequiredArgsConstructor
// @Transactional(readOnly = true)
// public class CollectionServiceImpl implements CollectionService {
//
//    private final CollectionRepository collectionRepository;
//
//    @Override
//    @Transactional
//    public void create(AddCollectionRequest request) {
//        collectionRepository.save(Collection.of(request.getName(), request.getDescription()));
//    }
//
//    @Override
//    @Transactional
//    public UpdateCollectionResponse update(UpdateCollectionRequest request) throws
// BookmarkException {
//        Collection collection =
//                collectionRepository
//                        .findByIdAndUserId(request.getCollectionId(), request.getUserId())
//                        .orElseThrow(
//                                () -> new
// CollectionNotFoundException(BookmarkErrorCode.COLLECTION_NOT_FOUND));
//
//        collection.update(request.getName(), request.getDescription());
//
//        return UpdateCollectionResponse.from(collection);
//    }
//
//    @Override
//    public GetAllCollectionResponse readAllByUser(long userId) {
//        List<GetAllCollectionResponse.Item> collections =
//                collectionRepository.findAllByUserId(userId).stream()
//                        .map(
//                                (collection ->
//                                        GetAllCollectionResponse.Item.from(
//                                                collection.getName(),
// collection.getDescription())))
//                        .toList();
//
//        return GetAllCollectionResponse.from(collections);
//    }
// }
