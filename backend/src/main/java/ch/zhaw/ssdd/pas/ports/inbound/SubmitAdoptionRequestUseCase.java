package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

public interface SubmitAdoptionRequestUseCase {
    AdoptionRequest submitAdoptionRequest(UserId adopterId, PetId petId);
}
