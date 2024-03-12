package drawer.server.domain.bookmark.repository.query;

import static drawer.server.domain.bookmark.entity.QBookmark.bookmark;
import static drawer.server.domain.bookmark.entity.QBookmarkCollection.bookmarkCollection;
import static drawer.server.domain.bookmark.entity.QCollection.collection;

import com.querydsl.jpa.impl.JPAQueryFactory;
import drawer.server.domain.bookmark.entity.Bookmark;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BookmarkRepositoryImpl implements CustomBookmarkRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Bookmark> findAllByUserUuidAndCollectionUuid(String userId, String collectionId) {
        return queryFactory
                .select(bookmark)
                .from(collection)
                .join(collection.bookmarkCollection, bookmarkCollection)
                .join(bookmarkCollection.bookmark, bookmark)
                .where(collection.user.uuid.eq(userId))
                .where(bookmarkCollection.collection.uuid.eq(collectionId))
                .where(bookmarkCollection.deleteFlag.isDeleted.eq(false))
                .fetch();
    }
}
