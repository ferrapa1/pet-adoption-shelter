package ch.zhaw.ssdd.pas.domain.shared;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.Objects;

@ValueObject
public record LocalFilePath(String localPath) {

    public LocalFilePath {
        Objects.requireNonNull(localPath);
        if (localPath.isBlank()) {
            throw new IllegalArgumentException(localPath);
        }
    }
}
