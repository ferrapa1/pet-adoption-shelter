package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.shared.SwissPhoneNumber;
import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.*;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterAdopterCommand;
import ch.zhaw.ssdd.pas.ports.outbound.AdopterPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AdopterManagementServiceTest {

    private AdopterManagementService service;

    @Mock
    private AdopterPersistence adopterPersistence;

    @BeforeEach
    void setUp() {
        service = new AdopterManagementService(adopterPersistence);
    }

    @Test
    void testLoad() {
        String uuidString = UUID.randomUUID().toString();
        UserId targetId = new UserId(uuidString);
        
        // Provide valid dummy objects to satisfy the Adopter constructor's non-null requirements
        ContactData dummyContact = new ContactData(new EmailAddress("dummy@mail.com"), new SwissPhoneNumber("+41 76 123 45 67"));
        Address dummyAddress = new Address("Hauptstrasse", "15A", new SwissPlz(8001), "Zürich");
        
        Adopter expectedAdopter = new Adopter(targetId, dummyContact, dummyAddress);
        
        Mockito.when(adopterPersistence.findByUserId(targetId)).thenReturn(expectedAdopter);

        Adopter loadedAdopter = service.load(targetId);
        
        assertNotNull(loadedAdopter);
        assertEquals(targetId, loadedAdopter.getUserId());
        assertEquals(dummyContact, loadedAdopter.getContactData());
        assertEquals(dummyAddress, loadedAdopter.getAddress());
    }

    public static RegisterAdopterCommand buildDummyRegisterCommand() {
        return new RegisterAdopterCommand(
                UUID.randomUUID().toString(),
                new ContactData(
                        new EmailAddress("dummy@mail.com"),
                        new SwissPhoneNumber("+41 76 123 45 67")
                ),
                new Address("Hauptstrasse", "15A", new SwissPlz(8001), "Zürich"),
                true,
                true
        );
    }
}
