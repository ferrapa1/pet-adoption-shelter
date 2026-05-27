package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

@ValueObject
public record SwissPlz(int value) {

    public SwissPlz {
        if (value < 1000 || value > 9999) {
            throw new IllegalArgumentException("Plz not valid.");
        }
    }
}
