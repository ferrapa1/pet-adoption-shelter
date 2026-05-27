package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.PetId;
import ch.zhaw.ssdd.pas.domain.UserId;

public interface SubmitAdoptionRequestUseCase {
    AdoptionRequest submitAdoptionRequest(UserId adopterId, PetId petId);
}
