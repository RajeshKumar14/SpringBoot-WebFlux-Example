package com.rajitblog.service;

import com.rajitblog.dao.BookRepository;
import com.rajitblog.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class BookService {
    @Autowired
    public BookRepository bookRepository;
    public Flux<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    public Mono<Book> getBookById(String id) {
        return bookRepository.findById(id);
    }
    public Mono<Book> addBook(Book book) {

        return bookRepository.save(book);
    }
    public Mono<Book> updateBook(Book book) {
        return Mono.just(new Book(book.getId(), book.getName() +" - updated"));
    }
    public Mono<Boolean> deleteBookById(int id) {
        System.out.println("Book deleted with id " + id);
        return Mono.just(true);
    }
}
