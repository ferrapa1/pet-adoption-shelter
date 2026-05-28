package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.ports.inbound.CreatePetCommand;
import ch.zhaw.ssdd.pas.ports.inbound.CreatePetUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.UUID;

/**
 * Implements the use case for creating and managing Pet aggregates.
 */
@Service
@Transactional
public class PetManagementService implements CreatePetUseCase {

    private final PetPersistence petPersistence;

    public PetManagementService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }

    @Override
    public Pet createPet(CreatePetCommand command) {
        // Generate a new unique ID for the pet
        PetId newPetId = new PetId(UUID.randomUUID().toString());

        // Create the new Pet aggregate using the full constructor with empty lists
        Pet newPet = new Pet(
                newPetId,
                command.shelterId(),
                command.dateOfBirth(),
                command.breed(),
                command.name(),
                Collections.emptyList(), // A new pet has no pictures yet
                Collections.emptyList()  // A new pet has no comments yet
        );

        // Here you could add more business logic, like checking if the shelter is valid, etc.

        // Persist the new aggregate using the outbound port
        return petPersistence.save(newPet);
    }
}
