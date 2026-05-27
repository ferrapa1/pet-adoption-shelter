package ch.zhaw.ssdd.pas.domain.adoption;

import ch.zhaw.ssdd.pas.domain.adoption.model.AdoptionRequestId;
import ch.zhaw.ssdd.pas.domain.adoption.model.AdoptionRequestStatus;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.stereotypes.Aggregate;
import ch.zhaw.ssdd.pas.stereotypes.EntityId;

import java.time.LocalDateTime;

@Aggregate.Root
public class AdoptionRequest {

    @EntityId
    private final AdoptionRequestId adoptionRequestId;
    private final UserId adopterId;
    private final PetId petId;
    private final LocalDateTime createdAt;

    private AdoptionRequestStatus requestStatus;

    public AdoptionRequest(AdoptionRequestId adoptionRequestId, UserId adopterId, PetId petId) {
        this.adoptionRequestId = adoptionRequestId;
        this.adopterId = adopterId;
        this.petId = petId;
        this.requestStatus = AdoptionRequestStatus.SUBMITTED;
        this.createdAt = LocalDateTime.now();
    }


    // Getters and Setters
    public AdoptionRequestId getAdoptionRequestId() {
        return adoptionRequestId;
    }

    public UserId getAdopterId() {
        return adopterId;
    }

    public PetId getPetId() {
        return petId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public AdoptionRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(AdoptionRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
