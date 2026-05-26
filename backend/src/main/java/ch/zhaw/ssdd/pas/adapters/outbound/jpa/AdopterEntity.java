package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADOPTER")
public class AdopterEntity extends UserEntity {
}
