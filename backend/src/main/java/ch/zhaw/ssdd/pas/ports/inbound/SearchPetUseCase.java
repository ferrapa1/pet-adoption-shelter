package ch.zhaw.ssdd.pas.ports.inbound;

import ch.zhaw.ssdd.pas.domain.pet.Pet;

import java.util.List;

public interface SearchPetUseCase {

    List<Pet> search(String searchText);
}
