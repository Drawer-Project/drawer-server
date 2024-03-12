package drawer.server.domain.verification.service.impl;

import drawer.server.domain.verification.exception.CollectionNameConflictException;
import drawer.server.domain.verification.repository.CollectionVerificationRepository;
import drawer.server.domain.verification.service.VerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VerificationServiceImpl implements VerificationService {

    private final CollectionVerificationRepository collectionVerificationRepository;

    @Override
    public void verifyCollectionName(String userId, String name) {
        checkNameExistence(userId, name);
    }

    private void checkNameExistence(String userId, String name) {
        if (collectionVerificationRepository.findByNameAndUserId(name, userId).isPresent()) {
            throw new CollectionNameConflictException();
        }
    }
}
