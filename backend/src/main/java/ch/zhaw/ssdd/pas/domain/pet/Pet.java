package ch.zhaw.ssdd.pas.domain.pet;

import ch.zhaw.ssdd.pas.domain.pet.model.*;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.Aggregate;
import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Aggregate.Root
public class Pet {

    @EntityId
    private final PetId petId;
    private final UserId shelterId;
    private final LocalDate dateOfBirth;
    private final Breed breed;
    private final String name;
    private List<PetPhoto> petPhotos; // Changed to mutable list
    private List<Comment> comments; // Changed to mutable list
    private PetAdoptionStatus adoptionStatus;

    public Pet(PetId petId,
               UserId shelterId,
               LocalDate dateOfBirth,
               Breed breed,
               String name,
               List<PetPhoto> petPhotos,
               List<Comment> comments) {
        this.petId = petId;
        this.shelterId = shelterId;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.name = name;
        this.petPhotos = new ArrayList<>(petPhotos); // Use mutable list
        this.comments = new ArrayList<>(comments); // Use mutable list
        this.adoptionStatus = PetAdoptionStatus.AVAILABLE;
    }

    public void addPicture(PetPhoto newPhoto) {
        if (newPhoto == null) {
            throw new IllegalArgumentException("PetPhoto cannot be null.");
        }
        this.petPhotos.add(newPhoto);
    }

    public void removePicture(String photoId) {
        if (photoId == null || photoId.isBlank()) {
            throw new IllegalArgumentException("Photo ID cannot be empty.");
        }
        this.petPhotos.removeIf(p -> p.localFilePath().localPath().equals(photoId)); // Assuming localPath is the ID
    }
    
    public void addComment(Comment newComment) {
        if (newComment == null) {
            throw new IllegalArgumentException("Comment cannot be null.");
        }
        this.comments.add(newComment);
    }

    public PetId getPetId() {
        return petId;
    }

    public UserId getShelterId() {
        return shelterId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public Breed getBreed() {
        return breed;
    }

    public String getName() {
        return name;
    }

    public PetAdoptionStatus getAdoptionStatus() {
        return adoptionStatus;
    }

    public List<PetPhoto> getPetPhotos() {
        return List.copyOf(petPhotos);
    }

    public List<Comment> getComments() {
        return List.copyOf(comments);
    }
}
