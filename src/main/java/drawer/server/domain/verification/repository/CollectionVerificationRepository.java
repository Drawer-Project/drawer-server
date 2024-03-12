package drawer.server.domain.verification.repository;

import drawer.server.domain.bookmark.entity.Collection;
import drawer.server.domain.verification.repository.query.CustomVerificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CollectionVerificationRepository
        extends JpaRepository<Collection, Long>, CustomVerificationRepository {}
