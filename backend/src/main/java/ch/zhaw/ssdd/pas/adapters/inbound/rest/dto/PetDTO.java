package ch.zhaw.ssdd.pas.adapters.inbound.rest.dto;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import org.antlr.v4.runtime.misc.NotNull;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.stream.Collectors;

public record PetDTO(
        @NonNull String id,
        @NotNull String name,
        @NonNull String species,
        @NonNull String breed,
        @NonNull String description,
        @NonNull PetAdoptionStatus adoptionStatus,
        @NonNull List<String> pictureUrls
) {

    public static PetDTO of(Pet pet) {
        List<String> urls = pet.getPetPhotos().stream()
                .map(photo -> photo.localFilePath().localPath())
                .collect(Collectors.toList());

        return new PetDTO(
                pet.getPetId().value().toString(),
                pet.getName(),
                pet.getSpecies().value(),
                pet.getBreed().value(),
                pet.getDescription(),
                pet.getAdoptionStatus(),
                urls
        );
    }
}
