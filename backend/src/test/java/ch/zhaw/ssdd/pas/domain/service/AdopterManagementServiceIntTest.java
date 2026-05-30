package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.shared.SwissPhoneNumber;
import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.*;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterAdopterCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdopterManagementServiceIntTest {

    @Autowired
    private AdopterManagementService service;

    @Test
    void testRegisterAndLoadAdopter() {
        RegisterAdopterCommand command = new RegisterAdopterCommand(
                new ContactData(
                        new EmailAddress("mail@test.com"),
                        new SwissPhoneNumber(("+41 67 123 45 67"))
                        ),
                new Address("street", "1A", new SwissPlz(8001), "Züri"),
                true,
                true
        );

        UserId userId = service.registerAdopter(command);
        assertNotNull(userId);
        Adopter adopter = service.load(userId);
        assertNotNull(adopter);
        assertNotNull(adopter.getContactData());
        ContactData contactData = adopter.getContactData();
        assertNotNull(contactData.email());
        assertEquals("mail@test.com", contactData.email().value());
        assertNotNull(contactData.phone());
        assertEquals("+41 67 123 45 67", contactData.phone().value());
        assertNotNull(adopter.getAddress());
        Address address = adopter.getAddress();
        assertEquals("street", address.street());
        assertEquals("1A", address.houseNumber());
        assertEquals("Züri", address.city());
        assertNotNull(address.plz());
        assertEquals(8001, address.plz().value());
        assertTrue(adopter.hasChildren());
        assertTrue(adopter.hasGarden());
    }
}
