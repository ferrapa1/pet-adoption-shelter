package ch.zhaw.ssdd.pas.ports.inbound.dto;

import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

import java.util.Objects;

/**
 * A command object that encapsulates all the information needed to submit a new adoption request.
 */
public record SubmitAdoptionRequestCommand(UserId adopterId, PetId petId) {
    public SubmitAdoptionRequestCommand {
        Objects.requireNonNull(adopterId, "Adopter ID cannot be null.");
        Objects.requireNonNull(petId, "Pet ID cannot be null.");
    }
}
