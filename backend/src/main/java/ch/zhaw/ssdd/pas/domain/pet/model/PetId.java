package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;

import static org.springframework.util.StringUtils.hasLength;

@EntityId
public record PetId(String value) {

    public PetId {
        if (!hasLength(value)) {
            throw new IllegalArgumentException("The pet id cannot be empty.");
        }
    }
}
