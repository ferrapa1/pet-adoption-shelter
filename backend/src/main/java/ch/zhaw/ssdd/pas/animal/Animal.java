package ch.zhaw.ssdd.pas.animal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Animal {

    @Id
    private Long id;

    private String name;
    private String race;
    private int age;

    protected Animal() {
    }

    public Animal(Long id, String name, String race, int age) {
        this.id = id;
        this.name = name;
        this.race = race;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getRace() {
        return race;
    }

    public int getAge() {
        return age;
    }
}