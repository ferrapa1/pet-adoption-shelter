package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.adapter;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AddressEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity.AdopterEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository.AdopterEntityRepository;
import ch.zhaw.ssdd.pas.domain.shared.SwissPhoneNumber;
import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.*;
import ch.zhaw.ssdd.pas.ports.outbound.AdopterPersistence;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.adapter.UserPersistenceAdapter.mapAddressFromDomain;
import static java.lang.String.format;

@Service
public class AdopterPersistenceAdapter implements AdopterPersistence {

    private final AdopterEntityRepository adopterEntityRepository;

    public AdopterPersistenceAdapter(AdopterEntityRepository adopterEntityRepository) {
        this.adopterEntityRepository = adopterEntityRepository;
    }

    @Override
    public UserId persistAdopter(Adopter adopter) {
        AdopterEntity entity = mapFromDomain(adopter);
        adopterEntityRepository.save(entity);
        return adopter.getUserId();
    }

    @Override
    public Adopter findByUserId(UserId userId) {
        Optional<AdopterEntity> entity = adopterEntityRepository.findByUserId(userId.value());
        if (entity.isEmpty()) {
            throw new NoSuchElementException(format("Cannot find AdopterEntity with userId=%s", userId));
        }
        return mapFromEntity(entity.get());
    }

    @Override
    public boolean existsByUserId(UserId userId) {
        return adopterEntityRepository.findByUserId(userId.value()).isPresent();
    }

    @Override
    public boolean existsByEmail(EmailAddress email) {
        return adopterEntityRepository.existsByEmail(email.value());
    }

    private static AdopterEntity mapFromDomain(Adopter adopter) {
        AdopterEntity entity = new AdopterEntity();
        entity.setId(UUID.randomUUID());
        entity.setUserId(adopter.getUserId().value());
        entity.setEmail(adopter.getContactData().email().value());
        entity.setPhoneNumber(adopter.getContactData().phone().value());
        entity.setAddress(mapAddressFromDomain(adopter.getAddress()));
        entity.setHasChildren(adopter.hasChildren());
        entity.setHasGarden(adopter.hasGarden());
        return entity;
    }

    private static Adopter mapFromEntity(AdopterEntity entity) {
        AddressEntity addressEntity = entity.getAddress();
        return new Adopter(
                new UserId(entity.getUserId()),
                new ContactData(
                        new EmailAddress(entity.getEmail()),
                        new SwissPhoneNumber(entity.getPhoneNumber())),
                new Address(
                        addressEntity.getStreet(),
                        addressEntity.getHouseNumber(),
                        new SwissPlz(addressEntity.getPlz()),
                        addressEntity.getCity()))
                .withChildren(entity.hasChildren())
                .withGarden(entity.hasGarden());
    }
}
