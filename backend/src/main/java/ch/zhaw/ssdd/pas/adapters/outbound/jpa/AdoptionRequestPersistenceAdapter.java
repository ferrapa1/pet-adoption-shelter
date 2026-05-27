package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.AdoptionRequestId;
import ch.zhaw.ssdd.pas.domain.PetId;
import ch.zhaw.ssdd.pas.domain.UserId;
import ch.zhaw.ssdd.pas.ports.outbound.AdoptionRequestPersistence;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AdoptionRequestPersistenceAdapter implements AdoptionRequestPersistence {

    private final AdoptionRequestEntityRepository adoptionRequestEntityRepository;

    public AdoptionRequestPersistenceAdapter(AdoptionRequestEntityRepository adoptionRequestEntityRepository) {
        this.adoptionRequestEntityRepository = adoptionRequestEntityRepository;
    }

    @Override
    public AdoptionRequest save(AdoptionRequest request) {
        AdoptionRequestEntity entity = toEntity(request);
        AdoptionRequestEntity savedEntity = adoptionRequestEntityRepository.save(entity);
        return toDomain(savedEntity);
    }

    private AdoptionRequestEntity toEntity(AdoptionRequest domain) {
        return new AdoptionRequestEntity(
                UUID.fromString(domain.getAdoptionRequestId().value()),
                UUID.fromString(domain.getAdopterId().value()),
                UUID.fromString(domain.getPetId().value()),
                domain.getCreatedAt(),
                domain.getRequestStatus()
        );
    }

    private AdoptionRequest toDomain(AdoptionRequestEntity entity) {
        return new AdoptionRequest(
                new AdoptionRequestId(entity.getId().toString()),
                new UserId(entity.getAdopterId().toString()),
                new PetId(entity.getPetId().toString())
        );
    }
}
