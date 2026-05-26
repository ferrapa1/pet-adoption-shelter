package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.Aggregate;

import java.time.LocalDate;

@Aggregate.Root
public class Pet {

    private final PetId petId;
    private final UserId shelterId;
    private final LocalDate dateOfBirth;
    private final Breed breed;
    private final String name;

    private AdoptionStatus adoptionStatus;

    public Pet(PetId petId, UserId shelterId, LocalDate dateOfBirth, Breed breed, String name) {
        this.petId = petId;
        this.shelterId = shelterId;
        this.dateOfBirth = dateOfBirth;
        this.breed = breed;
        this.name = name;
        adoptionStatus = AdoptionStatus.AVAILABLE;
    }
}
