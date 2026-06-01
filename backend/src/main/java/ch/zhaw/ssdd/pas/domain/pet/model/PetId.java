package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;
import java.util.UUID;

@EntityId
public record PetId(UUID value) {

    public PetId {
        Objects.requireNonNull(value, "PetId cannot be null.");
    }

    public static PetId of(String petIdStr) {
        // If the input is not a valid UUID, an IllegalArgumentException is thrown.
        return new PetId(UUID.fromString(petIdStr));
    }
}
