package ch.zhaw.ssdd.pas.domain.pet;

import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetPhoto;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.Aggregate;
import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.time.LocalDate;
import java.util.List;

@Aggregate.Root
public class Pet {

    @EntityId
    private final PetId petId;
    private final UserId shelterId;
    private final LocalDate dateOfBirth;
    private final Breed breed;
    private final String name;
    private final List<PetPhoto> petPhotos;

    private PetAdoptionStatus adoptionStatus;

    public Pet(PetId petId, UserId shelterId, LocalDate dateOfBirth, Breed breed, String name, List<PetPhoto> petPhotos) {
        this.petId = petId;
        this.shelterId = shelterId;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.name = name;
        this.petPhotos = List.copyOf(petPhotos);
        adoptionStatus = PetAdoptionStatus.AVAILABLE;
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
}
