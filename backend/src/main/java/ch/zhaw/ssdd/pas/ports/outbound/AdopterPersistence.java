package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

public interface AdopterPersistence {
    UserId persistAdopter(Adopter adopter);
    Adopter findByUserId(UserId userId);
}
