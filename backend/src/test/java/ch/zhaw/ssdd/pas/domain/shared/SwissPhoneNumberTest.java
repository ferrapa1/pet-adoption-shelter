package ch.zhaw.ssdd.pas.domain.shared;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class SwissPhoneNumberTest {

    @Test
    void testValidNumber() {
        SwissPhoneNumber phoneNumber = new SwissPhoneNumber("+41 76 123 45 67");
        assertEquals("+41 76 123 45 67", phoneNumber.value());
    }

    @Test
    void testNoPrefix() {
        assertThrows(IllegalArgumentException.class, () ->
                new SwissPhoneNumber("076 123 45 67"));
    }

    @Test
    void testNotFormatted() {
        assertThrows(IllegalArgumentException.class, () ->
                new SwissPhoneNumber("+41761234567"));
    }

    @Test
    void testWithChars() {
        assertThrows(IllegalArgumentException.class, () ->
                new SwissPhoneNumber("+41 76 123 45 6x"));
    }

    @Test
    void testNull() {
        assertThrows(NullPointerException.class, () ->
                new SwissPhoneNumber(null));
    }

    @Test
    void testEmptyString() {
        assertThrows(IllegalArgumentException.class, () ->
                new SwissPhoneNumber(""));
    }
}
