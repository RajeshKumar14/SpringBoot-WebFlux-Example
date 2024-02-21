package com.rajitblog.controller;

import com.rajitblog.model.Book;
import com.rajitblog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value="/books", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Flux<ResponseEntity<Book>> getAllBooks() {
        return bookService.getAllBooks()
                .map(list -> new ResponseEntity<>(list, HttpStatus.OK));
    }
    @GetMapping("/books/{id}")
    public Mono<ResponseEntity<Book>> getBookById(@PathVariable("id") String id) {
        return bookService.getBookById(id)
                .map(book -> new ResponseEntity<Book>(book, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping(value = "/add", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<ResponseEntity<Void>> addBook(@RequestBody Book book, UriComponentsBuilder builder) {
        return bookService.addBook(book)
                .map(newBook -> {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setLocation(builder.path("/books/{id}").buildAndExpand(newBook.getId()).toUri());
                    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
                });
    }
    @PutMapping(value = "/update", consumes = { MediaType.APPLICATION_JSON_VALUE })
    public Mono<ResponseEntity<Book>> updateBook(@RequestBody Book book) {
        return bookService.updateBook(book)
                .map(modBook -> new ResponseEntity<Book>(modBook, HttpStatus.OK));
    }
    @DeleteMapping("/books/{id}")
    public Mono<ResponseEntity<Void>> deleteBookById(@PathVariable("id") Integer id) {
        return bookService.deleteBookById(id)
                .map(val -> {
                    if (val == true) {
                        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
                    }
                    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                });
    }

}
