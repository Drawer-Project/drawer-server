package drawer.server.domain.bookmark.repository.query;

import static drawer.server.domain.bookmark.entity.QBookmark.bookmark;
import static drawer.server.domain.bookmark.entity.QBookmarkCollection.bookmarkCollection;
import static drawer.server.domain.bookmark.entity.QCollection.collection;

import com.querydsl.jpa.impl.JPAQueryFactory;
import drawer.server.domain.bookmark.entity.Collection;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CollectionRepositoryImpl implements CustomCollectionRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public List<Collection> findAllByUserUuid(String userId) {
        return queryFactory
                .select(collection)
                .from(bookmark)
                .join(bookmark.bookmarkCollection, bookmarkCollection)
                .join(bookmarkCollection.collection, collection)
                .where(bookmark.user.uuid.eq(userId))
                .where(collection.deleteFlag.isDeleted.eq(false))
                .fetch();
    }
}
