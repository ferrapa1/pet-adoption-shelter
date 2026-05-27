package ch.zhaw.ssdd.pas.domain;

import ch.zhaw.ssdd.pas.annotations.Aggregate;

import java.time.LocalDateTime;

@Aggregate.Root
public class AdoptionRequest {

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
