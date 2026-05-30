package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity.AdopterEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AdopterEntityRepository extends JpaRepository<AdopterEntity, UUID> {

    Optional<AdopterEntity> findByUserId(String userId);

    boolean existsByEmail(String email);
}
