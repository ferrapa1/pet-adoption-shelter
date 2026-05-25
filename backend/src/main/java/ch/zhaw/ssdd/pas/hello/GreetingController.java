package ch.zhaw.ssdd.pas.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {

    @GetMapping("/api/greeting")
    public Greeting greeting(@RequestParam(name = "name", defaultValue = "World") String name) {
        return new Greeting("Hello " + name + "!");
    }
}
