package drawer.server.domain.bookmark.repository;

import drawer.server.domain.bookmark.entity.Bookmark;
import drawer.server.domain.bookmark.repository.query.CustomBookmarkRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookmarkRepository
        extends JpaRepository<Bookmark, Long>, CustomBookmarkRepository {

    @Query("SELECT b FROM Bookmark b WHERE b.uuid = :bookmarkId AND b.deleteFlag.isDeleted = false")
    Optional<Bookmark> findByUuid(String bookmarkId);

    @Query(
            "SELECT b FROM Bookmark b WHERE b.uuid = :bookmarkId AND b.user.uuid = :userId AND b.deleteFlag.isDeleted = false")
    Optional<Bookmark> findByUuidAndUserUuid(String bookmarkId, String userId);

    @Query("SELECT b FROM Bookmark b WHERE b.user.uuid = :userId AND b.deleteFlag.isDeleted = false")
    List<Bookmark> findAllByUserUuid(String userId);
}
