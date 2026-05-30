package ch.zhaw.ssdd.pas.adapters.outbound.jpa.user.adapter;

import ch.zhaw.ssdd.pas.adapters.outbound.jpa.AddressEntity;
import ch.zhaw.ssdd.pas.domain.user.model.Address;
import ch.zhaw.ssdd.pas.ports.outbound.UserPersistence;

import java.util.UUID;

public class UserPersistenceAdapter implements UserPersistence {

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
