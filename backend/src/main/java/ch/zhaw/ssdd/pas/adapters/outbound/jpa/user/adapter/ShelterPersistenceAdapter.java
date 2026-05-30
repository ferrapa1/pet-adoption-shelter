package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.adapter;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.entity.ShelterEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository.ShelterEntityRepository;
import ch.zhaw.ssdd.pas.domain.user.Shelter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.outbound.ShelterPersistence;
import org.springframework.stereotype.Service;

import static ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.adapter.UserPersistenceAdapter.mapAddressFromDomain;

@Service
public class ShelterPersistenceAdapter implements ShelterPersistence {

    private final ShelterEntityRepository shelterEntityRepository;

    public ShelterPersistenceAdapter(ShelterEntityRepository shelterEntityRepository) {
        this.shelterEntityRepository = shelterEntityRepository;
    }

    @Override
    public UserId persistShelter(Shelter shelter) {
        ShelterEntity entity = mapFromDomain(shelter);
        shelterEntityRepository.save(entity);
        return shelter.getUserId();
    }

    private static ShelterEntity mapFromDomain(Shelter shelter) {
        ShelterEntity entity = new ShelterEntity(shelter.getUserId().value());
        entity.setEmail(shelter.getContactData().email().value());
        entity.setPhoneNumber(shelter.getContactData().phone().value());
        entity.setAddress(mapAddressFromDomain(shelter.getAddress()));
        return entity;
    }
}
