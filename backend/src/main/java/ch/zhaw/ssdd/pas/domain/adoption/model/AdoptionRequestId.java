package ch.zhaw.ssdd.pas.domain.adoption.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;

@EntityId
public record AdoptionRequestId(String value) {

    public AdoptionRequestId{
        Objects.requireNonNull(value);
    }
}
