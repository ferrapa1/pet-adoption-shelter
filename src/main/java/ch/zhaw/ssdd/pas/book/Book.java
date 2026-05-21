package ch.zhaw.ssdd.pas.book;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Book {

    @Id
    private Long id;

    private String title;

    protected Book() {
    }

    public Book(Long id, String title) {
        this.id = id;
        this.title = title;
    }

     public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

}
