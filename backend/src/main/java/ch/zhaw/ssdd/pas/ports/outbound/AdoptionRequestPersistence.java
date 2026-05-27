package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;

public interface AdoptionRequestPersistence {
    AdoptionRequest save(AdoptionRequest request);
}
