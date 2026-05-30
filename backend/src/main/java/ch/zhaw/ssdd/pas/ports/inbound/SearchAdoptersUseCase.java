package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.user.Adopter;

import java.util.List;

public interface SearchAdoptersUseCase {
    List<Adopter> searchAdopters();
}
