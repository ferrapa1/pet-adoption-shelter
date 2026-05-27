package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;

@EntityId
public record PetId(String value) {

    public PetId {
        Objects.requireNonNull(value);
    }
}
