package ch.zhaw.ssdd.pas.adapters.inbound.rest;

import ch.zhaw.ssdd.pas.adapters.inbound.rest.dto.PetDTO;
import ch.zhaw.ssdd.pas.domain.pet.Pet;
import ch.zhaw.ssdd.pas.domain.pet.model.PetId;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.AddPictureToPetUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.CreatePetUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.SearchPetUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.dto.CreatePetCommand;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import static ch.zhaw.ssdd.pas.adapters.inbound.rest.PetController.BASE_PATH;

@RestController
@RequestMapping(value = BASE_PATH)
public class PetController {

    public static final String BASE_PATH = "/api/pets";

    private final SearchPetUseCase searchPetUseCase;
    private final AddPictureToPetUseCase addPictureToPetUseCase;
    private final CreatePetUseCase createPetUseCase;

    public PetController(SearchPetUseCase searchPetUseCase, AddPictureToPetUseCase addPictureToPetUseCase, CreatePetUseCase createPetUseCase) {
        this.searchPetUseCase = searchPetUseCase;
        this.addPictureToPetUseCase = addPictureToPetUseCase;
        this.createPetUseCase = createPetUseCase;
    }

    @GetMapping()
    public List<PetDTO> getAllPets(@RequestParam(required = false) String searchText) {
        return searchPetUseCase.search(searchText)
                .stream()
                .map(PetDTO::of)
                .toList();
    }

    @PostMapping
    public ResponseEntity<PetId> createPet(@RequestBody CreatePetCommand command) {
        Pet newPet = createPetUseCase.createPet(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newPet.getPetId().value())
                .toUri();

        return ResponseEntity.created(location).body(newPet.getPetId());
    }

    @PostMapping("/{petId}/pictures")
    public ResponseEntity<Void> uploadPetPicture(
            @PathVariable String petId,
            @RequestParam("shelterId") String shelterId,
            @RequestParam("file") MultipartFile file) throws IOException {

        addPictureToPetUseCase.addPictureToPet(
                new PetId(UUID.fromString(petId)),
                new UserId(UUID.fromString(shelterId)),
                file.getBytes(),
                file.getOriginalFilename()
        );

        return ResponseEntity.ok().build();
    }
}
