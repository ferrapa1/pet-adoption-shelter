package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.Objects;

@ValueObject
public record Address(String street, String houseNumber, SwissPlz plz, String city) {

    public Address {
        Objects.requireNonNull(street, "Street cannot be null.");
    }
}
