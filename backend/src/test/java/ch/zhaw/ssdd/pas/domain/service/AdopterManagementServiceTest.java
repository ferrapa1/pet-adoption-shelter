package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AddressEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AdopterEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AdopterEntityRepository;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AdopterPersistenceAdapter;
import ch.zhaw.ssdd.pas.domain.shared.SwissPhoneNumber;
import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        AdopterManagementService.class,
        AdopterPersistenceAdapter.class})
class AdopterManagementServiceTest {

    @Autowired
    private AdopterManagementService service;

    @MockitoBean
    private AdopterEntityRepository repository;

    @Test
    void testRegisterAdopter() {
        UserId userId = service.registerAdopter(buildDummyAdopter());

        assertEquals(new UserId("dummy-adopter-id"), userId);
    }

    @Test
    void testLoad() {
        Mockito.when(repository.findByUserId("dummy-adopter-id")).thenReturn(Optional.of(buildDummyAdopterEntity()));

        Adopter adopter = service.load(new UserId("dummy-adopter-id"));
        assertNotNull(adopter);
        assertEquals(new UserId("dummy-adopter-id"), adopter.getUserId());
        assertEquals(new ContactData(new EmailAddress("dummy@mail.com"), new SwissPhoneNumber("+41 76 098 76 54")), adopter.getContactData());
        assertEquals(new Address("Dummy street", "33B", new SwissPlz(8000), "City"), adopter.getAddress());
        assertTrue(adopter.hasChildren());
        assertTrue(adopter.hasGarden());
    }

    public static Adopter buildDummyAdopter() {
        return new Adopter(
                new UserId("dummy-adopter-id"),
                new ContactData(
                        new EmailAddress("dummy@mail.com"),
                        new SwissPhoneNumber("+41 76 123 45 67")
                ),
                new Address("Hauptstrasse", "15A", new SwissPlz(8001), "Zürich"));
    }

    public static AdopterEntity buildDummyAdopterEntity() {
        AdopterEntity entity = new AdopterEntity();
        entity.setId(UUID.randomUUID());
        entity.setUserId("dummy-adopter-id");
        entity.setEmail("dummy@mail.com");
        entity.setPhoneNumber("+41 76 098 76 54");
        entity.setAddress(buildDummyAddressEntity());
        entity.setHasGarden(true);
        entity.setHasChildren(true);
        return entity;
    }

    public static AddressEntity buildDummyAddressEntity() {
        AddressEntity entity = new AddressEntity();
        entity.setId(UUID.randomUUID());
        entity.setStreet("Dummy street");
        entity.setHouseNumber("33B");
        entity.setCity("City");
        entity.setPlz(8000);
        return entity;
    }
}
