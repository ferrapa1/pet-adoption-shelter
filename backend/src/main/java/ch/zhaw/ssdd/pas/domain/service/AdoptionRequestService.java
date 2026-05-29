package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.adoption.model.AdoptionRequestId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.ports.inbound.SubmitAdoptionRequestCommand;
import ch.zhaw.ssdd.pas.ports.inbound.SubmitAdoptionRequestUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.AdoptionRequestPersistence;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;

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
    public AdoptionRequest submitAdoptionRequest(SubmitAdoptionRequestCommand command) {
        PetId petId = command.petId();
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        if (pet.getAdoptionStatus() != PetAdoptionStatus.AVAILABLE) {
            throw new IllegalStateException("Pet is not available for adoption.");
        }

        AdoptionRequestId newId = new AdoptionRequestId(UUID.randomUUID().toString());
        AdoptionRequest newRequest = new AdoptionRequest(newId, command.adopterId(), petId);

        return adoptionRequestPersistence.save(newRequest);
    }
}
