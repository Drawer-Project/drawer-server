package drawer.server.domain.bookmark.repository;

import drawer.server.domain.bookmark.entity.BookmarkCollection;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkCollectionRepository extends JpaRepository<BookmarkCollection, Long> {

    @Query(
            "SELECT bc FROM BookmarkCollection bc WHERE bc.bookmark.uuid = :bookmarkId AND bc.deleteFlag.isDeleted = false")
    Optional<BookmarkCollection> findByBookmarkUuid(String bookmarkId);

    @Query(
            "SELECT bc FROM BookmarkCollection bc WHERE bc.bookmark.uuid = :bookmarkId AND bc.collection.uuid = :collectionId AND bc.deleteFlag.isDeleted = false")
    Optional<BookmarkCollection> findByBookmarkUuidAndCollectionUuid(
            String bookmarkId, String collectionId);
}
