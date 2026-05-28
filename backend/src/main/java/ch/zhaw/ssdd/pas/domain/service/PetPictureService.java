package ch.zhaw.ssdd.pas.domain.service;


import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetPhoto;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.AddPictureToPetUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.RemovePictureFromPetUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implements the use cases for managing pictures on a pet.
 */
@Service
@Transactional
public class PetPictureService implements AddPictureToPetUseCase, RemovePictureFromPetUseCase {

    private final PetPersistence petPersistence;

    public PetPictureService(PetPersistence petPersistence) {
        this.petPersistence = petPersistence;
    }

    @Override
    public Pet addPictureToPet(PetId petId, UserId shelterId, PetPhoto petPhoto) {
        // 1. Load the aggregate
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        // 2. Enforce authorization: Only the owning shelter can add pictures
        if (!pet.getShelterId().equals(shelterId)) {
            throw new SecurityException("User is not authorized to add pictures to this pet.");
        }

        // 3. Call the aggregate's method to perform the business logic
        pet.addPicture(petPhoto);

        // 4. Save the updated aggregate
        return petPersistence.save(pet);
    }

    @Override
    public Pet removePictureFromPet(PetId petId, UserId shelterId, String pictureId) {
        // 1. Load the aggregate
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        // 2. Enforce authorization: Only the owning shelter can remove pictures
        if (!pet.getShelterId().equals(shelterId)) {
            throw new SecurityException("User is not authorized to remove pictures from this pet.");
        }

        // 3. Call the aggregate's method to perform the business logic
        pet.removePicture(pictureId);

        // 4. Save the updated aggregate
        return petPersistence.save(pet);
    }
}
