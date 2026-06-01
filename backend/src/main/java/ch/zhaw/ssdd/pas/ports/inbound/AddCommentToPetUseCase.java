package ch.zhaw.ssdd.pas.ports.inbound;


import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.Comment;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

/**
 * Defines the contract for adding a comment to a pet's profile.
 * This is an inbound port in the Hexagonal Architecture.
 */
public interface AddCommentToPetUseCase {

    /**
     * Adds a comment to the specified pet. Can be a top-level comment or a reply.
     *
     * @param petId The ID of the pet to comment on.
     * @param comment The Comment value object to add.
     * @return The updated Pet aggregate with the new comment.
     */
    Pet addCommentToPet(PetId petId, Comment comment);
}