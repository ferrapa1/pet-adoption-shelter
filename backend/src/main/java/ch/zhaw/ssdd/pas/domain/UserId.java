package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.EntityId;

import java.util.Objects;

@EntityId
public record UserId(String value) {
    public UserId {
        Objects.requireNonNull(value, "UserId cannot be null.");
    }
}
