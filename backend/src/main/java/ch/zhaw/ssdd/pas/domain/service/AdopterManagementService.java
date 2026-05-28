package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.LoadAdopterUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterAdopterUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.AdopterPersistence;
import org.springframework.stereotype.Service;

@Service
public class AdopterManagementService implements RegisterAdopterUseCase, LoadAdopterUseCase {

    private final AdopterPersistence adopterPersistence;

    public AdopterManagementService(AdopterPersistence adopterPersistence) {
        this.adopterPersistence = adopterPersistence;
    }

    @Override
    public UserId registerAdopter(Adopter adopter) {
       return adopterPersistence.persistAdopter(adopter);
    }

    @Override
    public Adopter load(UserId userId) {
        return adopterPersistence.findByUserId(userId);
    }
}
