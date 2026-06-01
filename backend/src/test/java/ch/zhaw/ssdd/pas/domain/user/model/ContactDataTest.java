package ch.zhaw.ssdd.pas.domain.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ContactDataTest {

    @Test
    void testValid() {
        EmailAddress email = new EmailAddress("mail@test.com");
        SwissPhoneNumber phone = new SwissPhoneNumber("+41 76 123 45 67");
        ContactData contactData = new ContactData(email, phone);
        assertNotNull(contactData.email());
        assertNotNull(contactData.phone());
        assertEquals("mail@test.com", contactData.email().value());
        assertEquals("+41 76 123 45 67", contactData.phone().value());
    }

    @Test
    void testNullEmail() {
        SwissPhoneNumber phone = new SwissPhoneNumber("+41 76 123 45 67");
        assertThrows(NullPointerException.class, () ->
                new ContactData(null, phone)
        );
    }

    @Test
    void testNullPhone() {
        EmailAddress email = new EmailAddress("mail@test.com");
        assertThrows(NullPointerException.class, () ->
                new ContactData(email, null)
        );
    }

    @Test
    void testNull() {
        assertThrows(NullPointerException.class, () ->
                new ContactData(null, null)
        );
    }

}
