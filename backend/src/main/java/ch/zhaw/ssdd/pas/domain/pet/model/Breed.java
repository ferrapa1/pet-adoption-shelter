package ch.zhaw.ssdd.pas.domain.pet.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import static org.springframework.util.StringUtils.hasLength;

@ValueObject
public record Breed(String value) {

    public Breed {
        if (!hasLength(value)) {
            throw new IllegalArgumentException("The breed cannot be empty.");
        }
    }
}
