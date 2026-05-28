package ch.zhaw.ssdd.pas.ports.inbound;



import ch.zhaw.ssdd.pas.domain.pet.Pet;

/**
 * Defines the contract for creating a new Pet.
 * This is an inbound port in the Hexagonal Architecture.
 */
public interface CreatePetUseCase {

    /**
     * Creates a new Pet and persists it based on the provided command.
     *
     * @param command The command object containing all necessary data.
     * @return The newly created Pet aggregate.
     */
    Pet createPet(CreatePetCommand command);
}
