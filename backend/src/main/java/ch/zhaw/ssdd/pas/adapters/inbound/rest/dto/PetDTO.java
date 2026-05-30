package ch.zhaw.ssdd.pas.adapters.inbound.rest.dto;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import org.antlr.v4.runtime.misc.NotNull;
import org.jspecify.annotations.NonNull;

public record PetDTO(
        @NotNull String name,
        @NonNull String species,
        @NonNull String breed,
        @NonNull PetAdoptionStatus adoptionStatus
) {

    public static PetDTO of(Pet pet) {
        return new PetDTO(
                pet.getName(),
                pet.getSpecies().value(),
                pet.getBreed().value(),
                pet.getAdoptionStatus());
    }
}
