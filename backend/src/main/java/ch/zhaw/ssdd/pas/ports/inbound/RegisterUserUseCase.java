package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.User;
import ch.zhaw.ssdd.pas.domain.UserId;

public interface RegisterUserUseCase {
    UserId registerUser(User user);
}
