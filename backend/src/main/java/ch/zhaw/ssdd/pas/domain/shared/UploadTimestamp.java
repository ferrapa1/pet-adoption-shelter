package ch.zhaw.ssdd.pas.domain.shared;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.time.LocalDateTime;
import java.util.Objects;

@ValueObject
public record UploadTimestamp(LocalDateTime value) {

    public UploadTimestamp {
        Objects.requireNonNull(value);
        if (value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException("The upload timestamp cannot be in the future.");
        }
    }
}
