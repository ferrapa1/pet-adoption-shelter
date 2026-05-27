package ch.zhaw.ssdd.pas.domain.shared;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.regex.Pattern;

@ValueObject
public record SwissPhoneNumber(String value) {

    public static final String REGEX = "^\\+(41)\\s(\\d{2})\\s(\\d{3})\\s(\\d{2})\\s(\\d{2})$";

    public SwissPhoneNumber {
        if(!Pattern.matches(REGEX, value)) {
            throw new IllegalArgumentException("Invalid swiss phone number. Must be like " + REGEX);
        }
    }
}
