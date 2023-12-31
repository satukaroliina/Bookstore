package BackEnd.Bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import BackEnd.Bookstore.model.Book;
import BackEnd.Bookstore.model.BookRepository;
import BackEnd.Bookstore.model.Category;
import BackEnd.Bookstore.model.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {
	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner bookStoreDemo(BookRepository brepository, CategoryRepository crepository) {
		return (args) -> {
			crepository.save(new Category("Drama"));
			crepository.save(new Category("Lifestyle"));
			crepository.save(new Category("Folklore"));
			crepository.save(new Category("Poetry"));
			
			brepository.save(new Book("Romeo ja Julia", "William Shakespeare", 1591, "1234567", 24.95, crepository.findByName("Drama").get(0)));
			brepository.save(new Book("Korkeintaan vähän väsynyt", "Eeva Kolu", 2021, "9375821", 14.95, crepository.findByName("Lifestyle").get(0)));
			brepository.save(new Book("Kansanuskon yöpuoli", "Pasi Klemettinen", 2022, "4729472", 25.95, crepository.findByName("Folklore").get(0)));
			brepository.save(new Book("The Sun and Her Flowers", "Rupi Kaur", 2017, "9583721", 16.40, crepository.findByName("Poetry").get(0)));

			log.info("fetch all books");
			for (Book book : brepository.findAll()) {
				log.info(book.toString());
			}
		};
	}
}
