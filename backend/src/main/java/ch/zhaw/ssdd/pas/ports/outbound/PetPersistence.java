package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.pet.model.PetSearchCriteria;

import java.util.List;
import java.util.Optional;

public interface PetPersistence {

    Optional<Pet> findById(PetId id);

    /**
     * Saves a Pet aggregate to the underlying persistence mechanism.
     *
     * @param pet The Pet to save.
     * @return The saved Pet.
     */
    Pet save(Pet pet);

    /**
     * Searches for pets based on the provided criteria.
     *
     * @param searchCriteria The criteria to filter pets.
     * @return A list of pets matching the criteria.
     */
    List<Pet> search(PetSearchCriteria searchCriteria);
}
