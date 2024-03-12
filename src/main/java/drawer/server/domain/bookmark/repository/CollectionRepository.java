package drawer.server.domain.bookmark.repository;

import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.repository.query.CustomCollectionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CollectionRepository
        extends JpaRepository<Collection, Long>, CustomCollectionRepository {

    @Query(
            "SELECT c FROM Collection c WHERE c.uuid = :collectionId AND c.deleteFlag.isDeleted = false")
    Optional<Collection> findByUuid(String collectionId);

    @Query(
            "SELECT c FROM Collection c WHERE c.uuid = :collectionId AND c.user.uuid = :userId AND c.deleteFlag.isDeleted = false")
    Optional<Collection> findByUuidAndUserUuid(String collectionId, String userId);

    @Query(
            "SELECT c FROM Collection c WHERE c.user.uuid = :userId AND c.deleteFlag.isDeleted = false")
    List<Collection> findByUserUuid(String userId);
}
