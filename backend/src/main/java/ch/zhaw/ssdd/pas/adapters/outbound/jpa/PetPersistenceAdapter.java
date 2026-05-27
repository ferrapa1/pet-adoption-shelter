package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.ports.outbound.PetPersistence;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetPersistenceAdapter implements PetPersistence {

    @Override
    public Optional<Pet> findById(PetId id) {
        //TODO
        return Optional.empty();
    }
}
