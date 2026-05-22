package ch.zhaw.ssdd.pas.animal;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AnimalsController {

    private final AnimalRepository repo;

    public AnimalsController(AnimalRepository repo) {
        this.repo = repo;
    }

    @GetMapping("/animals")
    public List<Animal> listAll() {
        return repo.findAll();
    }
}