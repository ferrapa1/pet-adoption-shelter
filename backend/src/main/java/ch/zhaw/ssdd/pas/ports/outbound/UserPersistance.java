package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.User;
import ch.zhaw.ssdd.pas.domain.UserId;

public interface UserPersistance {
    UserId persistUser(User user);
}
