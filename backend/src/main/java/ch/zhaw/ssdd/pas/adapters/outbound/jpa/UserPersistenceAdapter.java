package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.Address;
import ch.zhaw.ssdd.pas.domain.User;
import ch.zhaw.ssdd.pas.domain.UserId;
import ch.zhaw.ssdd.pas.ports.outbound.UserPersistance;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserPersistenceAdapter implements UserPersistance {

    private final UserEntityRepository userEntityRepository;

    public UserPersistenceAdapter(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    public UserId persistUser(User user) {
        UserEntity entity = mapFromDomain(user);
        userEntityRepository.save(entity);
        return user.getUserId();
    }

    private static UserEntity mapFromDomain(User user) {
        UserEntity entity = new UserEntity();
        entity.setId(UUID.fromString(user.getUserId().value()));
        entity.setUserId(user.getUserId().value());
        entity.setEmail(user.getContactData().email().value());
        entity.setPhoneNumber(user.getContactData().phone().value());
        entity.setAddress(mapAddressFromDomain(user.getAddress()));
        return entity;
    }

    private static AddressEntity mapAddressFromDomain(Address address) {
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(address.street());
        addressEntity.setCity(address.city());
        addressEntity.setPlz(address.plz().value());
        return addressEntity;
    }
}
