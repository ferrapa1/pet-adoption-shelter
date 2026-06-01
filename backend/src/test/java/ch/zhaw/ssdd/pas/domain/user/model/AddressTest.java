package ch.zhaw.ssdd.pas.domain.user.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AddressTest {

    @Test
    void testValid() {
        Address address = new Address("street", "1A", new SwissPlz(8001), "City");
        assertEquals("street", address.street());
        assertEquals("1A", address.houseNumber());
        assertNotNull(address.plz());
        assertEquals(8001, address.plz().value());
        assertEquals("City", address.city());
    }

    @Test
    void testInvalidStreet() {
        assertThrows(IllegalArgumentException.class, () ->
                new Address(null, "1A", new SwissPlz(8001), "City"));

        assertThrows(IllegalArgumentException.class, () ->
                new Address("", "1A", new SwissPlz(8001), "City"));
    }

    @Test
    void testInvalidHouseNumber() {
        assertThrows(IllegalArgumentException.class, () ->
                new Address("street", null, new SwissPlz(8001), "City"));

        assertThrows(IllegalArgumentException.class, () ->
                new Address("streez", "", new SwissPlz(8001), "City"));
    }

    @Test
    void testInvalidCity() {
        assertThrows(IllegalArgumentException.class, () ->
                new Address("street", "1A", new SwissPlz(8001), null));

        assertThrows(IllegalArgumentException.class, () ->
                new Address("streez", "1A", new SwissPlz(8001), ""));
    }

    @Test
    void testInvalidPlz() {
        assertThrows(NullPointerException.class, () ->
                new Address("street", "1A", null, "City"));
    }

}
