package drawer.server.domain.bookmark.repository;

import drawer.server.domain.bookmark.entity.Bookmark;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findByIdAndUserId(long userId, long bookmarkId);

    List<Bookmark> findAllByUserId(long userId);
}
