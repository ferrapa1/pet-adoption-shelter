package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.AdoptionRequestStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class AdoptionRequestEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID adopterId;

    @Column(nullable = false)
    private UUID petId;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AdoptionRequestStatus requestStatus;

    protected AdoptionRequestEntity() {
    }

    public AdoptionRequestEntity(UUID id, UUID adopterId, UUID petId, LocalDateTime createdAt, AdoptionRequestStatus requestStatus) {
        this.id = id;
        this.adopterId = adopterId;
        this.petId = petId;
        this.createdAt = createdAt;
        this.requestStatus = requestStatus;
    }

    // Getters and Setters
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(UUID adopterId) {
        this.adopterId = adopterId;
    }

    public UUID getPetId() {
        return petId;
    }

    public void setPetId(UUID petId) {
        this.petId = petId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public AdoptionRequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(AdoptionRequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }
}
