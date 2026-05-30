package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.util.Objects;

@EntityId
public record UserId(String value) {
    public UserId {
        Objects.requireNonNull(value, "UserId cannot be null.");
        if (value.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be empty.");
        }
        if (value.length() > 20) {
            throw new IllegalArgumentException("UserId cannot be longer than 20 characters.");
        }
    }
}
