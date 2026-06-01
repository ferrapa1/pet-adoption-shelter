package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for the PetEntity.
 */
public interface PetEntityRepository extends JpaRepository<PetEntity, UUID> {

    @Query("""
            select p from PetEntity p
            where
            upper(p.name) like %:searchTextUpperCase%
            or upper(p.species) like %:searchTextUpperCase%
            or upper(p.breed) like %:searchTextUpperCase%
            """)
    List<PetEntity> search(String searchTextUpperCase);
}
