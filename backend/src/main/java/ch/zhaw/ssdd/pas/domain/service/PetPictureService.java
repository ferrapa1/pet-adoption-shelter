package ch.zhaw.ssdd.pas.domain.service;


import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetPhoto;
import ch.zhaw.ssdd.pas.domain.shared.LocalFilePath;
import ch.zhaw.ssdd.pas.domain.shared.UploadTimestamp;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.AddPictureToPetUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.RemovePictureFromPetUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.FileStoragePort;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Implements the use cases for managing pictures on a pet.
 */
@Service
@Transactional
public class PetPictureService implements AddPictureToPetUseCase, RemovePictureFromPetUseCase {

    private final PetPersistence petPersistence;
    private final FileStoragePort fileStoragePort;

    public PetPictureService(PetPersistence petPersistence, FileStoragePort fileStoragePort) {
        this.petPersistence = petPersistence;
        this.fileStoragePort = fileStoragePort;
    }

    @Override
    public Pet addPictureToPet(PetId petId, UserId shelterId, byte[] fileData, String originalFilename) throws IOException {
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        if (!pet.getShelterId().equals(shelterId)) {
            throw new SecurityException("User is not authorized to add pictures to this pet.");
        }

        String fileUrl = fileStoragePort.saveFile(fileData, originalFilename);

        PetPhoto newPhoto = new PetPhoto(
                new LocalFilePath(fileUrl),
                new UploadTimestamp(LocalDateTime.now()),
                petId
        );

        pet.addPicture(newPhoto);

        return petPersistence.save(pet);
    }

    @Override
    public Pet removePictureFromPet(PetId petId, UserId shelterId, String pictureId) {
        Pet pet = petPersistence.findById(petId)
                .orElseThrow(() -> new IllegalArgumentException("Pet with ID " + petId.value() + " not found."));

        if (!pet.getShelterId().equals(shelterId)) {
            throw new SecurityException("User is not authorized to remove pictures from this pet.");
        }

        pet.removePicture(pictureId);
        // TODO do we want to remove the file from the disk?

        return petPersistence.save(pet);
    }
}
