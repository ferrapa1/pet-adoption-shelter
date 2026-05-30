package ch.zhaw.ssdd.pas.adapters.inbound.rest;

import ch.zhaw.ssdd.pas.adapters.inbound.rest.dto.PetDTO;
import ch.zhaw.ssdd.pas.ports.inbound.SearchPetUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static ch.zhaw.ssdd.pas.adapters.inbound.rest.PetRestController.BASE_PATH;

@RestController
@RequestMapping(BASE_PATH)
public class PetRestController {

    public static final String BASE_PATH = "/api/pets";

    private final SearchPetUseCase searchPetUseCase;

    public PetRestController(SearchPetUseCase searchPetUseCase) {
        this.searchPetUseCase = searchPetUseCase;
    }

    @GetMapping()
    public List<PetDTO> getAllPets(@RequestParam(required = false) String searchText) {
        return searchPetUseCase.search(searchText)
                .stream()
                .map(PetDTO::of)
                .toList();
    }
}
