package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;

public interface SubmitAdoptionRequestUseCase {
    AdoptionRequest submitAdoptionRequest(SubmitAdoptionRequestCommand command);
}
