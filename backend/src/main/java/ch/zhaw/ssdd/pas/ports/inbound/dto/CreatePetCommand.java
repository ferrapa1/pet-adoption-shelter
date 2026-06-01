package ch.zhaw.ssdd.pas.ports.inbound.dto;

import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.Species;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A command object that encapsulates all the information needed to create a new Pet.
 */
public record CreatePetCommand(
        UserId shelterId,
        String name,
        String description,
        LocalDate dateOfBirth,
        Species species,
        Breed breed
) {
    public CreatePetCommand {
        Objects.requireNonNull(shelterId, "Shelter ID cannot be null.");
        Objects.requireNonNull(name, "Name cannot be null.");
        Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null.");
        Objects.requireNonNull(species, "Species cannot be null.");
        Objects.requireNonNull(breed, "Breed cannot be null.");
    }
}
