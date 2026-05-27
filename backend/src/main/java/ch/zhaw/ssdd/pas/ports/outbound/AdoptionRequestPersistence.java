package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.AdoptionRequest;
import ch.zhaw.ssdd.pas.domain.AdoptionRequestId;

import java.util.Optional;

public interface AdoptionRequestPersistence {
    AdoptionRequest save(AdoptionRequest request);
}
