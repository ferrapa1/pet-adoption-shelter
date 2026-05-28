package ch.zhaw.ssdd.pas.adapters.inbound.rest.dto;

import ch.zhaw.ssdd.pas.domain.pet.Pet;

public record PetDTO(
        String name,
        String species,
        String breed,
        String adoptionStatus
) {

    public static PetDTO of(Pet pet) {
        return new PetDTO(
                pet.getName(),
                pet.getSpecies().value(),
                pet.getBreed().value(),
                pet.getAdoptionStatus().name());
    }
}
