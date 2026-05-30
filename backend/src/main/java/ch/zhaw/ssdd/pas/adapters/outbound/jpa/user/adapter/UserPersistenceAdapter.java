package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.adapter;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AddressEntity;
import ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.repository.UserEntityRepository;
import ch.zhaw.ssdd.pas.domain.user.model.Address;
import ch.zhaw.ssdd.pas.ports.outbound.UserPersistence;
import org.springframework.stereotype.Service;

import java.util.UUID;

//TODO
@Service
public class UserPersistenceAdapter implements UserPersistence {

    private final UserEntityRepository userEntityRepository;

    public UserPersistenceAdapter(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    protected static AddressEntity mapAddressFromDomain(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId(UUID.randomUUID());
        addressEntity.setStreet(address.street());
        addressEntity.setCity(address.city());
        addressEntity.setPlz(address.plz().value());
        addressEntity.setHouseNumber(address.houseNumber());
        return addressEntity;
    }
}
