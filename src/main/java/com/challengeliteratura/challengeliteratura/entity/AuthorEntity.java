package com.challengeliteratura.challengeliteratura.entity;
import com.challengeliteratura.challengeliteratura.model.Author;
import com.challengeliteratura.challengeliteratura.util.StringsUtil;

import jakarta.persistence.*;
@Entity
@Table(name = "Author")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer birthYear;
    private Integer deathYear;


    @OneToOne
    @JoinTable(
            name = "Book",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private BookEntity books;


    public AuthorEntity() {

    }

    public AuthorEntity(Author author) {
        this.name = StringsUtil.limitLength(author.name(), 200);

        if (author.birthYear() == null)
            this.birthYear = 1980;
        else
            this.birthYear = author.birthYear();

        if (author.deathYear() == null)
            this.deathYear = 3022;
        else
            this.deathYear = author.deathYear();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Integer getBirthYear() {
        return birthYear;
    }
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }
    public Integer getDeathYear() {
        return deathYear;
    }
    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    @Override
    public String toString() {
        return "AutorEntity [id=" + id + ", nome=" + name + ", Data de Nascimento=" + birthYear
                + ", Data de Morte=" + deathYear + ", livro="  + "]";
    }

    public BookEntity getBooks() {
        return books;
    }

    public void setBooks(BookEntity books) {
        this.books = books;
    }
}