package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ShelterEntityRepository extends JpaRepository<ShelterEntity, UUID> {
}
