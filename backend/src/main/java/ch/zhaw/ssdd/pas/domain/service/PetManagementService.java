package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetSearchCriteria;
import ch.zhaw.ssdd.pas.ports.inbound.CreatePetCommand;
import ch.zhaw.ssdd.pas.ports.inbound.CreatePetUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.SearchPetUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Implements the use case for creating and managing Pet aggregates.
 */
@Service
@Transactional
public class PetManagementService implements CreatePetUseCase, SearchPetUseCase {

    private final PetPersistence petPersistence;

    public PetManagementService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }

    @Override
    public Pet createPet(CreatePetCommand command) {
        PetId newPetId = new PetId(UUID.randomUUID());

        Pet newPet = new Pet(
                newPetId,
                command.shelterId(),
                command.dateOfBirth(),
                command.species(),
                command.breed(),
                command.name(),
                Collections.emptyList(),
                Collections.emptyList()
        );

        return petPersistence.save(newPet);
    }

    @Override
    public List<Pet> search(String searchText) {
        return petPersistence.search(new PetSearchCriteria(searchText));
    }
}
