package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdopterEntityRepository extends JpaRepository<AdopterEntity, UUID> {

    Optional<AdopterEntity> findByUserId(String userId);
}
