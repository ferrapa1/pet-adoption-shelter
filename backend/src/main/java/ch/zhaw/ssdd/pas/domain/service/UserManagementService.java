package ch.zhaw.ssdd.pas.domain.service;

import ch.zhaw.ssdd.pas.domain.User;
import ch.zhaw.ssdd.pas.domain.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterUserUseCase;
import ch.zhaw.ssdd.pas.ports.outbound.UserPersistance;

public class UserManagementService implements RegisterUserUseCase {

    private final UserPersistance userPersistance;

    public UserManagementService(UserPersistance userPersistance) {
        this.userPersistance = userPersistance;
    }

    @Override
    public UserId registerUser(User user) {
       return userPersistance.persistUser(user);
    }
}
