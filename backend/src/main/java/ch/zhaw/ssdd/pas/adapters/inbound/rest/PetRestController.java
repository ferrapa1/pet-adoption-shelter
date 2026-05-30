package ch.zhaw.ssdd.pas.adapters.inbound.rest;

import ch.zhaw.ssdd.pas.adapters.inbound.rest.dto.PetDTO;
import ch.zhaw.ssdd.pas.ports.inbound.SearchPetUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PetRestController {

    private final SearchPetUseCase searchPetUseCase;

    public PetRestController(SearchPetUseCase searchPetUseCase) {
        this.searchPetUseCase = searchPetUseCase;
    }

    @GetMapping("/api/pets")
    public List<PetDTO> getAllPets(@RequestParam(required = false) String searchText) {
        return searchPetUseCase.search(searchText)
                .stream()
                .map(PetDTO::of)
                .toList();
    }
}
