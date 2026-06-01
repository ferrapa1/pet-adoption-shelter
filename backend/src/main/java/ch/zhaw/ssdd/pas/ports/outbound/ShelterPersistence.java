package ch.zhaw.ssdd.pas.ports.outbound;

import ch.zhaw.ssdd.pas.domain.user.Shelter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;

public interface ShelterPersistence {
    UserId persistShelter(Shelter shelter);
}
