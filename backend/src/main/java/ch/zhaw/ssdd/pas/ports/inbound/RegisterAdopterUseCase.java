package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

public interface RegisterAdopterUseCase {
    UserId registerAdopter(Adopter adopter);
}
