package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.domain.shared.SwissPhoneNumber;
import ch.zhaw.ssdd.pas.stereotypes.ValueObject;

import java.util.Objects;

@ValueObject
public record ContactData(EmailAddress email, SwissPhoneNumber phone) {

    public ContactData {
        Objects.requireNonNull(email, "Email cannot be null.");
        Objects.requireNonNull(phone, "Phone number cannot be null.");
    }
}
