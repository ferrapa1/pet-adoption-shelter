package ch.zhaw.ssdd.pas.ports.inbound;


import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

/**
 * Defines the contract for removing a picture from a pet's profile.
 * This is an inbound port in the Hexagonal Architecture.
 */
public interface RemovePictureFromPetUseCase {

    /**
     * Removes a picture from the specified pet.
     *
     * @param petId The ID of the pet.
     * @param shelterId The ID of the shelter removing the picture (for authorization).
     * @param pictureId The ID of the picture to remove.
     * @return The updated Pet aggregate.
     */
    Pet removePictureFromPet(PetId petId, UserId shelterId, String pictureId);
}
