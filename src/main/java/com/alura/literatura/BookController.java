package com.alura.literatura;

//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    private final BookApiService bookApiService;

    public BookController(BookApiService bookApiService) {
        this.bookApiService = bookApiService;
    }

    @GetMapping("/book")
    public Book getBooks() {
        return bookApiService.fetchBooks();
    }
}

