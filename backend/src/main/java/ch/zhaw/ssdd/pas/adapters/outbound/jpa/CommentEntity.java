package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID authorId;

    @Column(nullable = false, length = 1000)
    private String content;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(name = "parent_id")
    private UUID parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adoption_id")
    private AdoptionRequestEntity adoptionRequest;

    protected CommentEntity() {}

    public CommentEntity(UUID id, UUID authorId, String content, LocalDateTime timestamp, UUID parentId, PetEntity pet, AdoptionRequestEntity adoptionRequest) {
        this.id = id;
        this.authorId = authorId;
        this.content = content;
        this.timestamp = timestamp;
        this.parentId = parentId;
        this.pet = pet;
        this.adoptionRequest = adoptionRequest;
    }

    public UUID getId() { return id; }
    public UUID getAuthorId() { return authorId; }
    public void setAuthorId(UUID authorId) { this.authorId = authorId; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
    public UUID getParentId() { return parentId; }
    public void setParentId(UUID parentId) { this.parentId = parentId; }
    public PetEntity getPet() { return pet; }
    public void setPet(PetEntity pet) { this.pet = pet; }
    public AdoptionRequestEntity getAdoptionRequest() { return adoptionRequest; }
    public void setAdoptionRequest(AdoptionRequestEntity adoptionRequest) { this.adoptionRequest = adoptionRequest; }
}
