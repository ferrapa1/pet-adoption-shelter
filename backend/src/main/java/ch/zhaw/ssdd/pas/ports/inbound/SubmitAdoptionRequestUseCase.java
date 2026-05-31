package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;
import ch.zhaw.ssdd.pas.ports.inbound.dto.SubmitAdoptionRequestCommand;

public interface SubmitAdoptionRequestUseCase {
    AdoptionRequest submitAdoptionRequest(SubmitAdoptionRequestCommand command);
}
