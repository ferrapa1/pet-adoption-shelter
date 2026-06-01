package ch.zhaw.ssdd.pas.ports.inbound;


import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

import java.io.IOException;

/**
 * Defines the contract for adding a picture to a pet's profile.
 * This is an inbound port in the Hexagonal Architecture.
 */
public interface AddPictureToPetUseCase {

    /**
     * Saves a picture file and associates it with the specified pet.
     *
     * @param petId The ID of the pet.
     * @param shelterId The ID of the shelter adding the picture (for authorization).
     * @param fileData The raw byte data of the picture file.
     * @param originalFilename The original name of the file.
     * @return The updated Pet aggregate.
     * @throws IOException If there is an error saving the file.
     */
    Pet addPictureToPet(PetId petId, UserId shelterId, byte[] fileData, String originalFilename) throws IOException;
}
