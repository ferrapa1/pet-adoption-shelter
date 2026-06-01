package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.Objects;
import java.util.regex.Pattern;

@ValueObject
public record SwissPhoneNumber(String value) {

    public static final String REGEX = "^\\+(41)\\s(\\d{2})\\s(\\d{3})\\s(\\d{2})\\s(\\d{2})$";

    public SwissPhoneNumber {
        Objects.requireNonNull(value, "Phone number cannot be null.");
        if(!Pattern.matches(REGEX, value)) {
            throw new IllegalArgumentException("Invalid swiss phone number. Must be like " + REGEX);
        }
    }
}
