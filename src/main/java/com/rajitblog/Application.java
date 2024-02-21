package com.rajitblog;

import com.rajitblog.client.BookWebClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		BookWebClient bwc = new BookWebClient();
		bwc.getAllBooksDemo();
//		bwc.getBookByIdDemo();
//		bwc.createBookDemo();
//		bwc.updateBookDemo();
//		bwc.deleteBookByIdDemo();
	}

}
