package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.Species;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.SubmitAdoptionRequestCommand;
import ch.zhaw.ssdd.pas.ports.outbound.AdoptionRequestPersistence;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class AdoptionRequestServiceTest {

    private AdoptionRequestService service;

    @Mock
    private AdoptionRequestPersistence adoptionRequestPersistence;

    @Mock
    private PetPersistence petPersistence;

    @BeforeEach
    void setUp() {
        service = new AdoptionRequestService(adoptionRequestPersistence, petPersistence);
    }

    @Test
    void testSubmitAdoptionRequest_Success() {
        PetId petId = new PetId(UUID.randomUUID());
        UserId adopterId = new UserId(UUID.randomUUID());
        SubmitAdoptionRequestCommand command = new SubmitAdoptionRequestCommand(adopterId, petId);

        Pet availablePet = new Pet(
                petId,
                new UserId(UUID.randomUUID()),
                LocalDate.now(),
                new Species("Dog"),
                new Breed("Golden Retriever"),
                "Fido",
                Collections.emptyList(),
                Collections.emptyList()
        );

        Mockito.when(petPersistence.findById(petId)).thenReturn(Optional.of(availablePet));
        Mockito.when(adoptionRequestPersistence.save(any(AdoptionRequest.class))).thenAnswer(i -> i.getArguments()[0]);

        AdoptionRequest savedRequest = service.submitAdoptionRequest(command);

        assertNotNull(savedRequest);
        assertEquals(petId, savedRequest.getPetId());
        assertEquals(adopterId, savedRequest.getAdopterId());
        Mockito.verify(petPersistence).findById(petId);
        Mockito.verify(adoptionRequestPersistence).save(any(AdoptionRequest.class));
    }

    @Test
    void testSubmitAdoptionRequest_PetNotAvailable() {
        PetId petId = new PetId(UUID.randomUUID());
        UserId adopterId = new UserId(UUID.randomUUID());
        SubmitAdoptionRequestCommand command = new SubmitAdoptionRequestCommand(adopterId, petId);

        Pet mockPet = Mockito.mock(Pet.class);
        Mockito.when(mockPet.getAdoptionStatus()).thenReturn(PetAdoptionStatus.ADOPTED);

        Mockito.when(petPersistence.findById(petId)).thenReturn(Optional.of(mockPet));

        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            service.submitAdoptionRequest(command);
        });

        assertEquals("Pet is not available for adoption.", exception.getMessage());
        Mockito.verify(petPersistence).findById(petId);
        Mockito.verify(adoptionRequestPersistence, Mockito.never()).save(any(AdoptionRequest.class));
    }
    
    @Test
    void testSubmitAdoptionRequest_PetNotFound() {
        PetId petId = new PetId(UUID.randomUUID());
        UserId adopterId = new UserId(UUID.randomUUID());
        SubmitAdoptionRequestCommand command = new SubmitAdoptionRequestCommand(adopterId, petId);

        Mockito.when(petPersistence.findById(petId)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.submitAdoptionRequest(command);
        });

        assertEquals("Pet with ID " + petId + " not found.", exception.getMessage());
        Mockito.verify(petPersistence).findById(petId);
        Mockito.verify(adoptionRequestPersistence, Mockito.never()).save(any());
    }
}
