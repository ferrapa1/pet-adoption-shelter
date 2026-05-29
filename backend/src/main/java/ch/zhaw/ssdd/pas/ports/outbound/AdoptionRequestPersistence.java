package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.adoption.AdoptionRequest;

public interface AdoptionRequestPersistence {

    /**
     * Saves an AdoptionRequest to the underlying persistence mechanism.
     *
     * @param request The AdoptionRequest to save.
     * @return The saved AdoptionRequest.
     */
    AdoptionRequest save(AdoptionRequest request);
}
