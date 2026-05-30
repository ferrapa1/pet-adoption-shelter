package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.EmailAddress;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

public interface AdopterPersistence {

    /**
     * Persists an Adopter to the underlying persistence mechanism.
     *
     * @param adopter The Adopter to persist.
     * @return The ID of the persisted Adopter.
     */
    UserId persistAdopter(Adopter adopter);

    /**
     * Finds an Adopter by their unique user ID.
     *
     * @param userId The ID of the user to find.
     * @return An Adopter if found, or null otherwise.
     */
    Adopter findByUserId(UserId userId);

    /**
     * Checks if an Adopter already exists with the given ID.
     *
     * @param userId The ID to check.
     * @return true if the ID exists, false otherwise.
     */
    boolean existsByUserId(UserId userId);

    /**
     * Checks if a user already exists with the given email.
     *
     * @param email The email to check.
     * @return true if the email exists, false otherwise.
     */
    boolean existsByEmail(EmailAddress email);
}
