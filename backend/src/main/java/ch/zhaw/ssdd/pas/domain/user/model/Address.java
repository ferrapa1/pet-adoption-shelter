package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.Objects;

import static org.springframework.util.StringUtils.hasLength;

@ValueObject
public record Address(String street, String houseNumber, SwissPlz plz, String city) {

    public Address {
        if (!hasLength(street)) {
            throw new IllegalArgumentException("The street cannot be empty.");
        }
        if (!hasLength(houseNumber)) {
            throw new IllegalArgumentException("The houseNumber cannot be empty.");
        }
        if (!hasLength(city)) {
            throw new IllegalArgumentException("The city cannot be empty.");
        }
        Objects.requireNonNull(plz, "The plz cannot be null.");
    }
}
