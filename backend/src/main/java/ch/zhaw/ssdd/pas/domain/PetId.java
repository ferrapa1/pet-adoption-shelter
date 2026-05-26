package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.EntityId;

import java.util.Objects;

@EntityId
public record PetId(String value) {

    public PetId {
        Objects.requireNonNull(value);
    }
}
