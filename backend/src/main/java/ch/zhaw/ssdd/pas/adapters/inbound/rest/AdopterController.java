package ch.zhaw.ssdd.pas.adapters.inbound.rest;

import ch.zhaw.ssdd.pas.domain.user.Adopter;
import ch.zhaw.ssdd.pas.domain.user.model.UserId;
import ch.zhaw.ssdd.pas.ports.inbound.LoadAdopterUseCase;
import ch.zhaw.ssdd.pas.ports.inbound.dto.RegisterAdopterCommand;
import ch.zhaw.ssdd.pas.ports.inbound.RegisterAdopterUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/adopters")
public class AdopterController {

    private final RegisterAdopterUseCase registerAdopterUseCase;
    private final LoadAdopterUseCase loadAdopterUseCase;

    public AdopterController(RegisterAdopterUseCase registerAdopterUseCase, LoadAdopterUseCase loadAdopterUseCase) {
        this.registerAdopterUseCase = registerAdopterUseCase;
        this.loadAdopterUseCase = loadAdopterUseCase;
    }

    @PostMapping
    public ResponseEntity<UserId> registerAdopter(@RequestBody RegisterAdopterCommand command) {
        UserId newUserId = registerAdopterUseCase.registerAdopter(command);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newUserId.value())
                .toUri();

        return ResponseEntity.created(location).body(newUserId);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Adopter> findAdopterById(@PathVariable String userId) {
        Adopter adopter = loadAdopterUseCase.load(UserId.of(userId));
        return ResponseEntity.ok(adopter);
    }
}
