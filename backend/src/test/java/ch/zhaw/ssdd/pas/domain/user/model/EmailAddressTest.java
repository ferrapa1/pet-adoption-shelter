package ch.zhaw.ssdd.pas.domain.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmailAddressTest {

    @Test
    void testValid() {
        EmailAddress mail = new EmailAddress("valid@mail.com");
        assertEquals("valid@mail.com", mail.value());
    }

    @Test
    void testNotValid() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmailAddress("@invalid.com"));
    }

    @Test
    void testNull() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmailAddress(null));
    }

    @Test
    void testEmptyString() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmailAddress(""));
    }
}
