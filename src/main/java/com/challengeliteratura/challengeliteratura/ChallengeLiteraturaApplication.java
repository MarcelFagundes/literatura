package com.challengeliteratura.challengeliteratura;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.challengeliteratura.challengeliteratura.client.ClientLiterature;
import com.challengeliteratura.challengeliteratura.repository.AuthorRepository;
import com.challengeliteratura.challengeliteratura.repository.BookRepository;

@SpringBootApplication
public class ChallengeLiteraturaApplication implements CommandLineRunner {
	@Autowired
	private BookRepository bookRepository;
	@Autowired
	private AuthorRepository authorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraturaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		ClientLiterature client = new ClientLiterature(bookRepository, authorRepository);
		client.menu();
	}
}