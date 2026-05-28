package ch.zhaw.ssdd.pas.ports.inbound;


import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetPhoto;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

/**
 * Defines the contract for adding a picture to a pet's profile.
 * This is an inbound port in the Hexagonal Architecture.
 */
public interface AddPictureToPetUseCase {

    /**
     * Adds a picture to the specified pet.
     *
     * @param petId The ID of the pet.
     * @param shelterId The ID of the shelter adding the picture (for authorization).
     * @param petPhoto The PetPhoto value object to add.
     * @return The updated Pet aggregate.
     */
    Pet addPictureToPet(PetId petId, UserId shelterId, PetPhoto petPhoto);
}
