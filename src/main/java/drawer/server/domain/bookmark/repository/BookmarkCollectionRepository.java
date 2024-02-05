package drawer.server.domain.bookmark.repository;

import drawer.server.domain.bookmark.entity.BookmarkCollection;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookmarkCollectionRepository extends JpaRepository<BookmarkCollection, Long> {

    BookmarkCollection findByBookmarkId(long id);
}
