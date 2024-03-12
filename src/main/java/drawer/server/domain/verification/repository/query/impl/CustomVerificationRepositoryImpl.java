package drawer.server.domain.verification.repository.query.impl;

import static drawer.server.domain.bookmark.entity.QCollection.collection;
import static drawer.server.domain.user.entity.QUser.user;

import com.querydsl.jpa.impl.JPAQueryFactory;
import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.verification.repository.query.CustomVerificationRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomVerificationRepositoryImpl implements CustomVerificationRepository {

    private final JPAQueryFactory queryFactory;

    @Override
    public Optional<Collection> findByNameAndUserId(String name, String userId) {

        return Optional.ofNullable(
                queryFactory
                        .select(collection)
                        .from(collection)
                        .join(collection.user, user)
                        .where(collection.deleteFlag.isDeleted.eq(false))
                        .where(collection.name.eq(name))
                        .where(user.uuid.eq(userId))
                        .fetchOne());
    }
}
