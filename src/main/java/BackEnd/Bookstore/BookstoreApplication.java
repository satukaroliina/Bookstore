package BackEnd.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BackEnd.Bookstore.model.Book;
import BackEnd.Bookstore.model.BookRepository;

@SpringBootApplication
public class BookstoreApplication {
	
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookStoreDemo(BookRepository repository) {
		return (args) -> {
			log.info("insert some data to H2 database");
			repository.save(new Book("Romeo ja Julia", "William Shakespeare", 1591, "1234567", 24.95));
			repository.save(new Book("Korkeintaan vähän väsynyt", "Eeva Kolu", 2021, "9375821", 14.95));
			repository.save(new Book("Kansanuskon yöpuoli", "Pasi Klemettinen", 2022, "4729472", 25.95));
			repository.save(new Book("The Sun and Her Flowers", "Rupi Kaur", 2017, "9583721", 16.40));

			log.info("fetch all books");
			for (Book book : repository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
