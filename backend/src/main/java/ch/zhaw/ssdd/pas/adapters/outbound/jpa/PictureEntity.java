package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "pet_picture")
public class PictureEntity {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pet_id")
    private PetEntity pet;

    protected PictureEntity() {}

    public PictureEntity(UUID id, String url, PetEntity pet) {
        this.id = id;
        this.url = url;
        this.pet = pet;
    }

    public UUID getId() { return id; }
    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }
    public PetEntity getPet() { return pet; }
    public void setPet(PetEntity pet) { this.pet = pet; }
}
