package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.AdoptionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdoptionRequestEntityRepository extends JpaRepository<AdoptionRequestEntity, UUID> {
}
