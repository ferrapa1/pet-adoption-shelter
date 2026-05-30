package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SHELTER")
public class ShelterEntity extends UserEntity {
}
