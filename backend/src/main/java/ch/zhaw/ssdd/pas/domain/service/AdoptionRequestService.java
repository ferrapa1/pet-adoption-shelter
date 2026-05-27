package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.*;
import ch.zhaw.ssdd.pas.ports.inbound.SubmitAdoptionRequestUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.AdoptionRequestPersistence;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class AdoptionRequestService implements SubmitAdoptionRequestUseCase {

    private final AdoptionRequestPersistence adoptionRequestPersistence;
    private final PetPersistence petPersistence;

    public AdoptionRequestService(AdoptionRequestPersistence adoptionRequestPersistence, PetPersistence petPersistence) {
        this.adoptionRequestPersistence = adoptionRequestPersistence;
        this.petPersistence = petPersistence;
    }

    @Override
    public AdoptionRequest submitAdoptionRequest(UserId adopterId, PetId petId) {
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        if (pet.getAdoptionStatus() != AdoptionStatus.AVAILABLE) {
            throw new IllegalStateException("Pet is not available for adoption.");
        }

        AdoptionRequestId newId = new AdoptionRequestId(UUID.randomUUID().toString());
        AdoptionRequest newRequest = new AdoptionRequest(newId, adopterId, petId);

        return adoptionRequestPersistence.save(newRequest);
    }
}
