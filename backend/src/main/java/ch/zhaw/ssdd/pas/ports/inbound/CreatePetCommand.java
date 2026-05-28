package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.pet.model.Breed;
import ch.zhaw.ssdd.pas.domain.pet.model.Species;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

import java.time.LocalDate;
import java.util.Objects;

/**
 * A command object that encapsulates all the information needed to create a new Pet.
 *
 * <p>Using a command object instead of passing the domain aggregate directly into the use case
 * is a core principle of Hexagonal Architecture and DDD. It provides several advantages:
 * <ul>
 *     <li><b>Separation of Concerns:</b> The web layer is only responsible for collecting and validating
 *     raw data, not for constructing a valid domain object.</li>
 *     <li><b>Prevents Logic Leakage:</b> The business logic for creating a Pet (including validation
 *     and any side effects) remains entirely within the application's core domain services.</li>
 *     <li><b>Enhanced Security:</b> The application core does not trust the outside world to provide a
 *     valid, consistent aggregate. It takes full responsibility for creating the aggregate from
 *     the raw data provided by the command.</li>
 * </ul>
 * </p>
 */
public record CreatePetCommand(
        UserId shelterId,
        String name,
        LocalDate dateOfBirth,
        Species species,
        Breed breed
) {
    public CreatePetCommand {
        Objects.requireNonNull(shelterId, "Shelter ID cannot be null.");
        Objects.requireNonNull(name, "Name cannot be null.");
        Objects.requireNonNull(dateOfBirth, "Date of birth cannot be null.");
        Objects.requireNonNull(species, "Species cannot be null.");
        Objects.requireNonNull(breed, "Breed cannot be null.");
    }
}
