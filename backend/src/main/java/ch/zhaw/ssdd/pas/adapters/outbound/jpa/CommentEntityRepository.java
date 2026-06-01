package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * Spring Data JPA repository for the CommentEntity.
 */
@Repository
public interface CommentEntityRepository extends JpaRepository<CommentEntity, UUID> {
}
