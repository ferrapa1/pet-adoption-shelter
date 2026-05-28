package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.Comment;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.ports.inbound.AddCommentToPetUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the use case for adding a comment to a pet.
 */
@Service
@Transactional
public class PetCommentService implements AddCommentToPetUseCase {

    private final PetPersistence petPersistence;

    public PetCommentService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }

    @Override
    public Pet addCommentToPet(PetId petId, Comment comment) {
        // 1. Load the aggregate from the database
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        // 2. Call the aggregate's method to perform the business logic
        pet.addComment(comment);

        // 3. Save the updated aggregate
        return petPersistence.save(pet);
    }
}
