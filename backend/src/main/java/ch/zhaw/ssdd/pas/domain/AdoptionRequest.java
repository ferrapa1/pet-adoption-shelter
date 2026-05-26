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
}
