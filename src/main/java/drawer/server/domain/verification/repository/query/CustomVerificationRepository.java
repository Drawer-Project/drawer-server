package drawer.server.domain.verification.repository.query;

import drawer.server.domain.bookmark.entity.Collection;
import java.util.Optional;

public interface CustomVerificationRepository {

    Optional<Collection> findByNameAndUserId(String name, String userId);
}
