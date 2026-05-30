package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.EmailAddress;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.LoadAdopterUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterAdopterCommand;
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
    public UserId registerAdopter(RegisterAdopterCommand command) {
        UserId userId = new UserId(command.requestedUserId());
        EmailAddress email = command.contactData().email();

        if (adopterPersistence.existsByUserId(userId)) {
            throw new IllegalStateException("User ID '" + userId.value() + "' is already taken.");
        }

        if (adopterPersistence.existsByEmail(email)) {
            throw new IllegalStateException("Email '" + email.value() + "' is already in use.");
        }

        Adopter newAdopter = new Adopter(userId, command.contactData(), command.address())
                .withGarden(command.hasGarden())
                .withChildren(command.hasChildren());

        return adopterPersistence.persistAdopter(newAdopter);
    }

    @Override
    public Adopter load(UserId userId) {
        return adopterPersistence.findByUserId(userId);
    }
}
