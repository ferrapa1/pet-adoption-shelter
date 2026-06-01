package ch.zhaw.ssdd.pas.domain.user.model;

import ch.zhaw.ssdd.pas.stereotypes.ValueObject;
import org.apache.commons.validator.routines.EmailValidator;

@ValueObject
public record EmailAddress(String value) {
    public EmailAddress {
        if (!EmailValidator.getInstance()
                .isValid(value)) {
            throw new IllegalArgumentException("Invalid email.");
        }
    }
}
