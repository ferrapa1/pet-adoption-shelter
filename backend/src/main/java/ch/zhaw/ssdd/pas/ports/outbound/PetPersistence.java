package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;

import java.util.Optional;

public interface PetPersistence {

    Optional<Pet> findById(PetId id);
}
