package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.adoption.model.AdoptionRequestId;
import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.Species;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
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
        // Arrange
        PetId petId = new PetId("dummy-doggy");
        UserId adopterId = new UserId("dummy-adopter-id");
        AdoptionRequest request = new AdoptionRequest(new AdoptionRequestId("temp-id"), adopterId, petId);

        Pet availablePet = new Pet(
                petId,
                new UserId("shelter-id"),
                LocalDate.now(),
                new Species("Dog"),
                new Breed("Golden Retriever"),
                "Fido",
                Collections.emptyList(),
                Collections.emptyList()
        );

        Mockito.when(petPersistence.findById(petId)).thenReturn(Optional.of(availablePet));
        Mockito.when(adoptionRequestPersistence.save(any(AdoptionRequest.class))).thenAnswer(i -> i.getArguments()[0]);

        // Act
        AdoptionRequest savedRequest = service.submitAdoptionRequest(request);

        // Assert
        assertNotNull(savedRequest);
        assertEquals(petId, savedRequest.getPetId());
        assertEquals(adopterId, savedRequest.getAdopterId());
        Mockito.verify(petPersistence).findById(petId);
        Mockito.verify(adoptionRequestPersistence).save(any(AdoptionRequest.class));
    }

    @Test
    void testSubmitAdoptionRequest_PetNotAvailable() {
        // Arrange
        PetId petId = new PetId("dummy-doggy");
        UserId adopterId = new UserId("dummy-adopter-id");
        AdoptionRequest request = new AdoptionRequest(new AdoptionRequestId("temp-id"), adopterId, petId);

        // Create a pet and simulate that it has already been adopted
        Pet unavailablePet = new Pet(
                petId,
                new UserId("shelter-id"),
                LocalDate.now(),
                new Species("Dog"),
                new Breed("Golden Retriever"),
                "Fido",
                Collections.emptyList(),
                Collections.emptyList()
        );
        // Note: In a real DDD setup, you'd have a method like unavailablePet.markAsAdopted()
        // For this test, we might need reflection if there's no setter, but let's assume it fails
        // if we just try to submit for a pet that isn't found for now, or we can mock a pet.
        
        // Let's create a mock pet that returns ADOPTED
        Pet mockPet = Mockito.mock(Pet.class);
        Mockito.when(mockPet.getAdoptionStatus()).thenReturn(PetAdoptionStatus.ADOPTED);

        Mockito.when(petPersistence.findById(petId)).thenReturn(Optional.of(mockPet));

        // Act & Assert
        IllegalStateException exception = assertThrows(IllegalStateException.class, () -> {
            service.submitAdoptionRequest(request);
        });

        assertEquals("Pet is not available for adoption.", exception.getMessage());
        Mockito.verify(petPersistence).findById(petId);
        Mockito.verify(adoptionRequestPersistence, Mockito.never()).save(any(AdoptionRequest.class));
    }
    
    @Test
    void testSubmitAdoptionRequest_PetNotFound() {
        // Arrange
        PetId petId = new PetId("dummy-doggy");
        UserId adopterId = new UserId("dummy-adopter-id");
        AdoptionRequest request = new AdoptionRequest(new AdoptionRequestId("temp-id"), adopterId, petId);

        Mockito.when(petPersistence.findById(petId)).thenReturn(Optional.empty());

        // Act & Assert
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.submitAdoptionRequest(request);
        });

        assertEquals("Pet with ID dummy-doggy not found.", exception.getMessage());
        Mockito.verify(petPersistence).findById(petId);
        Mockito.verify(adoptionRequestPersistence, Mockito.never()).save(any());
    }
}
