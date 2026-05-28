package ch.zhaw.ssdd.pas.domain.pet.model;

import static org.springframework.util.StringUtils.hasLength;

public record Species(String value) {

    public Species {
        if (!hasLength(value)) {
            throw new IllegalArgumentException("The species cannot be empty.");
        }
    }
}
