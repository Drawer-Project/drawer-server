package drawer.server.domain.bookmark.repository;

import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.bookmark.repository.query.CustomCollectionRepository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionRepository
        extends JpaRepository<Collection, Long>, CustomCollectionRepository {

    Optional<Collection> findById(long id);

    //    void deleteByIdAndUserId(long collectionId, long userId);

    //    Optional<Collection> findByIdAndUserId(long collectionId, long userId);
}
