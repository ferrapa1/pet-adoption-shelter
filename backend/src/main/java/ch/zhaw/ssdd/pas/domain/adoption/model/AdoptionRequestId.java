package ch.zhaw.ssdd.pas.domain.adoption.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;
import java.util.UUID;

@EntityId
public record AdoptionRequestId(String value) {

    public AdoptionRequestId {
        Objects.requireNonNull(value, "AdoptionRequestId value cannot be null");
        try {
            UUID.fromString(value);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("AdoptionRequestId must be a valid UUID string: " + value, e);
        }
    }
}
