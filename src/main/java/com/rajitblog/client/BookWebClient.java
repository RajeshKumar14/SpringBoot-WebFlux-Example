package com.rajitblog.client;

import com.rajitblog.model.Book;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class BookWebClient {
    private WebClient client = WebClient.create("http://localhost:8080");

    public void getAllBooksDemo() {
        Flux<Book> bookFlux= client.get()
                .uri("/books")
                .exchange()
                .flatMapMany(res -> res.bodyToFlux(Book.class));
        bookFlux.doOnNext(b->System.out.println(b.getName()));
    }
    public void getBookByIdDemo() {
        int id = 101;
        client.get()
                .uri("/books/" + id)
                .exchange()
                .flatMap(res -> res.bodyToMono(Book.class))
                .subscribe(book -> System.out.println("GET: " + book),
                        err -> System.out.println(err.getMessage()));
    }
    public void createBookDemo() {
        client.post()
                .uri("/add")
                .bodyValue(new Book("Spring"))
                .exchange()
                .subscribe(res -> System.out.println("POST: "
                        + res.statusCode() + ", " + res.headers().asHttpHeaders().getLocation()));
    }
    public void updateBookDemo() {
        client.put()
                .uri("/update")
                .bodyValue(new Book("103", "Android"))
                .exchange()
                .flatMap(res -> res.bodyToMono(Book.class))
                .subscribe(book -> System.out.println("PUT: " + book));
    }
    public void deleteBookByIdDemo() {
        int id = 104;
        client.delete()
                .uri("/books/" + id)
                .exchange()
                .subscribe(res -> System.out.println("DELETE: " + res.statusCode()));
    }
}
