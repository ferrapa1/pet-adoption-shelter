package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.Pet;
import ch.zhaw.ssdd.pas.domain.PetId;

import java.util.Optional;

public interface PetPersistence {

    Optional<Pet> findById(PetId id);
}
