package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.user.Shelter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterShelterUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.ShelterPersistence;
import org.springframework.stereotype.Service;

@Service
public class ShelterManagementService implements RegisterShelterUseCase {

    private final ShelterPersistence shelterPersistence;

    public ShelterManagementService(ShelterPersistence shelterPersistence) {
        this.shelterPersistence = shelterPersistence;
    }

    @Override
    public UserId registerShelter(Shelter shelter) {
        return shelterPersistence.persistShelter(shelter);
    }
}
