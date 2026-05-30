package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.UUID;

@Entity
@DiscriminatorValue("SHELTER")
public class ShelterEntity extends UserEntity {

    protected ShelterEntity() {

    }

    public ShelterEntity(UUID id) {
        super(id);
    }
}
