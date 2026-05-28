package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.PetAdoptionStatus;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pet")
public class PetEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private UUID shelterId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dateOfBirth;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "breed_name"))
    private Breed breed;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PetAdoptionStatus adoptionStatus;

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<CommentEntity> comments = new ArrayList<>();

    @OneToMany(mappedBy = "pet", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<PictureEntity> pictures = new ArrayList<>();

    // JPA requires a no-arg constructor
    protected PetEntity() {}

    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    public UUID getShelterId() { return shelterId; }
    public void setShelterId(UUID shelterId) { this.shelterId = shelterId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    public Breed getBreed() { return breed; }
    public void setBreed(Breed breed) { this.breed = breed; }
    public PetAdoptionStatus getAdoptionStatus() { return adoptionStatus; }
    public void setAdoptionStatus(PetAdoptionStatus adoptionStatus) { this.adoptionStatus = adoptionStatus; }
    public List<CommentEntity> getComments() { return comments; }
    public void setComments(List<CommentEntity> comments) { this.comments = comments; }
    public List<PictureEntity> getPictures() { return pictures; }
    public void setPictures(List<PictureEntity> pictures) { this.pictures = pictures; }
}