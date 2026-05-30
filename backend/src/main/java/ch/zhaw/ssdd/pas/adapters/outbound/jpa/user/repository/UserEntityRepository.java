package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserEntityRepository extends JpaRepository<UserEntity, UUID> {
    Optional<UserEntity> findByUserId(String userId);
}
