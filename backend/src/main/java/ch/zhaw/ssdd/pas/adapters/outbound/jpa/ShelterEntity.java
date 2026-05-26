package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("SHELTER")
public class ShelterEntity extends UserEntity {
}
