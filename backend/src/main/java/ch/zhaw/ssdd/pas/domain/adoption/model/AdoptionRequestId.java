package ch.zhaw.ssdd.pas.domain.adoption.model;

import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.EntityId;
import org.springframework.util.ObjectUtils;

import java.util.Objects;
import java.util.UUID;

@EntityId
public record AdoptionRequestId(UUID value) {

    public AdoptionRequestId {
        Objects.requireNonNull(value, "AdoptionRequestId value cannot be null");
    }

    public static AdoptionRequestId of(String adoptionRequestIdStr) {
        // If the input is not a valid UUID, an IllegalArgumentException is thrown.
        return new AdoptionRequestId(UUID.fromString(adoptionRequestIdStr));
    }
}
