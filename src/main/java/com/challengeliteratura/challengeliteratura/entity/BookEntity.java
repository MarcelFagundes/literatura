package com.challengeliteratura.challengeliteratura.entity;
import com.challengeliteratura.challengeliteratura.model.Author;
import com.challengeliteratura.challengeliteratura.model.Book;
import com.challengeliteratura.challengeliteratura.util.StringsUtil;
import jakarta.persistence.*;

@Entity
@Table(name = "Book")
public class BookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private Integer download;
    @OneToOne(mappedBy = "books", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private AuthorEntity author;

    public BookEntity() {}

    public BookEntity(Book book) {
        this.title = StringsUtil.limitLength(book.title(), 200);
        this.download = book.download();
        if (!book.languages().isEmpty())
            this.language = book.languages().get(0);
        if (!book.authors().isEmpty()) {
            for (Author author : book.authors()) {
                this.author = new AuthorEntity(author);
                break;
            }
        }
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLanguage() {
        return language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public Integer getDownload() {
        return download;
    }
    public void setDownload(Integer download) {
        this.download = download;
    }

    @Override
    public String toString() {
        return "BookEntity [id=" + id + ", título=" + title + ", idioma=" + language + ", download=" + download
                + ", autores=" + author + "]";
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void setAuthor(AuthorEntity author) {
        this.author = author;
    }
}