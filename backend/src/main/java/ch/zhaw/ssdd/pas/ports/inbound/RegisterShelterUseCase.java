package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.user.Shelter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

public interface RegisterShelterUseCase {
    UserId registerShelter(Shelter shelter);
}
