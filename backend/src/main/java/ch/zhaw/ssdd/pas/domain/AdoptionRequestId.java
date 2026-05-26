package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.EntityId;

import java.util.Objects;

@EntityId
public record AdoptionRequestId(String value) {

    public AdoptionRequestId{
        Objects.requireNonNull(value);
    }
}
