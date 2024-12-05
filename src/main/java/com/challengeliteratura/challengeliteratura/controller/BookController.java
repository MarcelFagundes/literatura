//package com.challengeliteratura.challengeliteratura.controller;
//
//import com.challengeliteratura.challengeliteratura.entity.BookEntity;
//import com.challengeliteratura.challengeliteratura.model.Book;
//import com.challengeliteratura.challengeliteratura.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/books")
//public class BookController {
//
//    @Autowired
//    private BookService bookService;
//
//
//    @PostMapping
//    public ResponseEntity<?> addBook(@RequestBody BookEntity book) {
////        try {
////            BookEntity savedBook = bookService.addBook(book);
////            return ResponseEntity.ok(savedBook);
////        } catch (IllegalArgumentException e) {
////            return ResponseEntity.badRequest().body(e.getMessage());
////        }
//        try {
//            BookEntity novoLivro = bookService.addBook(book);
//            return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
//        } catch (IllegalArgumentException e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }
//}