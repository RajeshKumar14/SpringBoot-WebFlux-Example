package com.rajitblog;

import com.rajitblog.controller.BookController;
import com.rajitblog.dao.BookRepository;
import com.rajitblog.model.Book;
import com.rajitblog.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Mono;

@ExtendWith(SpringExtension.class)
@WebFluxTest(controllers = BookController.class)
@Import(BookService.class)
class ApplicationTests {

	@MockBean
	private BookRepository bookRepository;

	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testAddBook() {
		Book book=new Book();
		book.setId(1);
		book.setName("Java");
		Mockito.when(bookRepository.save(book)).thenReturn(Mono.just(book));
		webTestClient.post().uri("/add").contentType(MediaType.APPLICATION_JSON)
				.body(BodyInserters.fromObject(book))
				.exchange()
				.expectStatus().isCreated();
		Mockito.verify(bookRepository,Mockito.times(1)).save(book);
	}

}
