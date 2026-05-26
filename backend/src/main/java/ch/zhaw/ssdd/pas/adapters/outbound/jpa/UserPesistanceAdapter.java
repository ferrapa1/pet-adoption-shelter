package ch.zhaw.ssdd.pas.adapters.outbound.jpa;

import ch.zhaw.ssdd.pas.domain.User;
import ch.zhaw.ssdd.pas.domain.UserId;
import ch.zhaw.ssdd.pas.ports.outbound.UserPersistance;
import org.springframework.stereotype.Service;

@Service
public class UserPesistanceAdapter implements UserPersistance {

    private final UserEntityRepository userEntityRepository;

    public UserPesistanceAdapter(UserEntityRepository userEntityRepository) {
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
        // TODO: IMPLEMENT!
        return entity;
    }
}
